package one.nem.lacerta.vcs.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import one.nem.lacerta.model.VcsLogModel;
import one.nem.lacerta.model.VcsRevModel;
import one.nem.lacerta.source.database.LacertaDatabase;
import one.nem.lacerta.source.database.entity.VcsLogEntity;
import one.nem.lacerta.source.database.entity.VcsRevEntity;
import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.vcs.ActionType;
import one.nem.lacerta.vcs.LacertaVcs;
import one.nem.lacerta.vcs.internal.JsonUtils;
import one.nem.lacerta.vcs.model.action.DeletePage;
import one.nem.lacerta.vcs.model.action.InsertPage;
import one.nem.lacerta.vcs.model.action.UpdatePage;

public class LacertaVcsImpl implements LacertaVcs {

    // TAG
    private static final String TAG = LacertaVcsImpl.class.getSimpleName();

    String documentId;

    LacertaDatabase database;

    LacertaLogger logger;

    @AssistedInject
    public LacertaVcsImpl(LacertaLogger logger, LacertaDatabase database, @Assisted String documentId) {
        this.logger = logger;
        this.database = database;
        this.documentId = documentId;
        logger.debug(TAG, "LacertaVcsImpl constructor");
    }

    @Override
    public void updatePage(int index, String fileName) {
        logger.debug(TAG, "updatePage");

        // UpdatePage
        UpdatePage updatePage = new UpdatePage(index, fileName);
        updatePage.setActionType(ActionType.UPDATE_PAGE);

        VcsLogEntity vcsLogEntity = new VcsLogEntity();
        vcsLogEntity.id = UUID.randomUUID().toString();
        vcsLogEntity.documentId = documentId;
        vcsLogEntity.branchName = "master";
        vcsLogEntity.createdAt = new java.util.Date();
        vcsLogEntity.actionType = ActionType.UPDATE_PAGE.getValue();
        vcsLogEntity.action = JsonUtils.toJson(updatePage);
        database.vcsLogDao().insert(vcsLogEntity);
    }

    @Override
    public void insertPage(int index, String fileName) {
        logger.debug(TAG, "insertPage");

        // InsertPage
        InsertPage insertPage = new InsertPage(index, fileName);
        insertPage.setActionType(ActionType.INSERT_PAGE);

        VcsLogEntity vcsLogEntity = new VcsLogEntity();
        vcsLogEntity.id = UUID.randomUUID().toString();
        vcsLogEntity.documentId = documentId;
        vcsLogEntity.branchName = "master";
        vcsLogEntity.createdAt = new java.util.Date();
        vcsLogEntity.actionType = ActionType.INSERT_PAGE.getValue();
        vcsLogEntity.action = JsonUtils.toJson(insertPage);
        database.vcsLogDao().insert(vcsLogEntity);
    }

    @Override
    public void deletePage(int index) {

    }

    @Override
    public void undo() {
        database.vcsLogDao().deleteLatestByDocumentId(documentId);
    }

    @Override
    public void createDocument(String documentId) {
        logger.debug(TAG, "createDocument");

        VcsLogEntity vcsLogEntity = new VcsLogEntity();
        vcsLogEntity.id = UUID.randomUUID().toString();
        vcsLogEntity.documentId = documentId;
        vcsLogEntity.branchName = "master";
        vcsLogEntity.createdAt = new java.util.Date();
        vcsLogEntity.actionType = ActionType.CREATE_DOCUMENT.getValue();
        vcsLogEntity.action = "";
        database.vcsLogDao().insert(vcsLogEntity);
    }

    // Internal
    private ArrayList<VcsLogEntity> getNonIncludedVcsLogEntities() {
        return new ArrayList<>(database.vcsLogDao().findByDocumentIdAndIncluded(documentId, false));
    }

    private ArrayList<VcsLogEntity> getNonIncludedVcsLogEntities(String branchName) {
        return new ArrayList<>(database.vcsLogDao().findByDocumentIdAndBranchNameAndIncluded(documentId, branchName, false));
    }

    private void setIncludedVcsLogEntities(ArrayList<VcsLogEntity> vcsLogEntities) {
        vcsLogEntities.forEach(vcsLogEntity -> {
            vcsLogEntity.isIncluded = true;
            database.vcsLogDao().update(vcsLogEntity);
        });

        logger.debug(TAG, "setIncludedVcsLogEntities updated: " + vcsLogEntities.size() + " entities");
    }

    @Override
    public void generateRevisionAtCurrent(String message) {
        logger.debug(TAG, "generateRevisionAtCurrent");
        // TODO-rca: ブランチを考慮する

        ArrayList<VcsLogEntity> vcsLogEntities = getNonIncludedVcsLogEntities();

        ArrayList<String> logIds = new ArrayList<>();
        vcsLogEntities.forEach(vcsLogEntity -> {
            logIds.add(vcsLogEntity.id);
        });

        VcsRevEntity vcsRevEntity = new VcsRevEntity();
        vcsRevEntity.id = UUID.randomUUID().toString();
        vcsRevEntity.documentId = documentId;
        vcsRevEntity.branchName = "master";
        vcsRevEntity.createdAt = new java.util.Date();
        vcsRevEntity.commitMessage = message;
        vcsRevEntity.logIds = logIds;

        database.vcsRevDao().insert(vcsRevEntity);

        setIncludedVcsLogEntities(vcsLogEntities);

        logger.debug(TAG, "generateRevisionAtCurrent finished");
        logger.debug(TAG, "New revision inserted: " + vcsRevEntity.id);
    }

    @Override
    public CompletableFuture<ArrayList<VcsRevModel>> getRevisionHistory() {
        return CompletableFuture.supplyAsync(() -> {
            logger.debug(TAG, "getRevisionHistoryByDocumentId");
            ArrayList<VcsRevModel> vcsRevModels = new ArrayList<>();

            List<VcsRevEntity> vcsRevEntities = database.vcsRevDao().findByDocumentId(this.documentId);
            vcsRevEntities.forEach(vcsRevEntity -> {
                VcsRevModel vcsRevModel = new VcsRevModel();
                vcsRevModel.setId(vcsRevEntity.id);
                vcsRevModel.setDocumentId(vcsRevEntity.documentId);
                vcsRevModel.setBranchName(vcsRevEntity.branchName);
                vcsRevModel.setCreatedAt(vcsRevEntity.createdAt);
                vcsRevModel.setCommitMessage(vcsRevEntity.commitMessage);
                vcsRevModel.setLogIds(vcsRevEntity.logIds);
                vcsRevModels.add(vcsRevModel);
            });

            return vcsRevModels;
        });
    }

    @Override
    public CompletableFuture<ArrayList<VcsLogModel>> getLogHistory() {
        return null;
    }

    @Override
    public CompletableFuture<ArrayList<VcsLogModel>> getLogHistoryInRev(String revId) {
        return CompletableFuture.supplyAsync(() -> {
            logger.debug(TAG, "getLogHistoryInRev");
            ArrayList<VcsLogModel> vcsLogModels = new ArrayList<>();

            VcsRevEntity vcsRevEntity = database.vcsRevDao().findById(revId);
            ArrayList<VcsLogEntity> vcsLogEntities = getLogInRevAsync(vcsRevEntity).join(); // TODO-rca: リファクタリング
            vcsLogEntities.forEach(vcsLogEntity -> {
                VcsLogModel vcsLogModel = new VcsLogModel();
                vcsLogModel.setId(vcsLogEntity.id);
                vcsLogModel.setDocumentId(vcsLogEntity.documentId);
                vcsLogModel.setBranchName(vcsLogEntity.branchName);
                vcsLogModel.setCreatedAt(vcsLogEntity.createdAt);
                vcsLogModel.setAction(vcsLogEntity.action);
                vcsLogModels.add(vcsLogModel);
            });

            return vcsLogModels;
        });
    }

    private CompletableFuture<ArrayList<VcsRevEntity>> getRevBeforeTargetIdAsync(String revId){
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<VcsRevEntity> vcsRevEntities = new ArrayList<>(database.vcsRevDao().findByDocumentId(this.documentId));
            ArrayList<VcsRevEntity> vcsRevEntitiesBeforeTarget = new ArrayList<>();
            vcsRevEntities.forEach(vcsRevEntity -> {
                if(vcsRevEntity.id.equals(revId)){
                    vcsRevEntitiesBeforeTarget.add(vcsRevEntity);
                    return;
                }
                vcsRevEntitiesBeforeTarget.add(vcsRevEntity);
            });
            logger.debug(TAG, "getRevBeforeTargetIdAsync finished\nResult size: " + vcsRevEntitiesBeforeTarget.size());
            return vcsRevEntitiesBeforeTarget;
        });
    }

    private CompletableFuture<ArrayList<VcsLogEntity>> getLogInRevsAsync(ArrayList<VcsRevEntity> vcsRevEntities){
        return CompletableFuture.supplyAsync(() -> {
            List<String> logIds = new ArrayList<>();
            vcsRevEntities.forEach(vcsRevEntity -> {
                logIds.addAll(vcsRevEntity.logIds);
            });
            // TODO-rca: ソートしないといけないかも（順番が保証されているわけではない + 順番が変わるとほぼ確実に壊れる）
            ArrayList<VcsLogEntity> vcsLogEntities = new ArrayList<>(database.vcsLogDao().findByIds(logIds));
            logger.debug(TAG, "getLogInRevsAsync finished\nResult size: " + vcsLogEntities.size());
            return vcsLogEntities;
        });
    }

    private CompletableFuture<ArrayList<VcsLogEntity>> getLogInRevAsync(VcsRevEntity revEntity) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<VcsLogEntity> vcsLogEntities = new ArrayList<>(database.vcsLogDao().findByIds(revEntity.logIds));
            logger.debug(TAG, "getLogInRevAsync finished\nResult size: " + vcsLogEntities.size());
            return vcsLogEntities;
        });
    }

    @Override
    public CompletableFuture<ArrayList<String>> getDocumentPagePathListRev(String revId) {
        return CompletableFuture.supplyAsync(() -> {
            logger.debug(TAG, "getDocumentPagePathListRev");
            ArrayList<VcsLogEntity> vcsLogEntities = getRevBeforeTargetIdAsync(revId).thenCompose(this::getLogInRevsAsync).join();

            ArrayList<String> fileNameList = new ArrayList<>();
            for(VcsLogEntity vcsLogEntity : vcsLogEntities){
                logger.debug(TAG, "getDocumentPagePathListRev: processing " + vcsLogEntity.id + "(Type: " + vcsLogEntity.actionType + ")");
                if (vcsLogEntity.actionType.equals(ActionType.INSERT_PAGE.getValue())){
                    InsertPage insertPage = (InsertPage) JsonUtils.fromJson(vcsLogEntity.action, ActionType.INSERT_PAGE);
                    logger.debug(TAG, "getDocumentPagePathListRev: Inserting " + insertPage.getFileName() + " at " + insertPage.getIndex());
                    if (fileNameList.size() <= insertPage.getIndex()) {
                        logger.debug(TAG, "Index out of range, appending");
                        fileNameList.add(insertPage.getFileName());
                    } else {
                        fileNameList.add(insertPage.getIndex(), insertPage.getFileName());
                    }
                } else if (vcsLogEntity.actionType.equals(ActionType.UPDATE_PAGE.getValue())){
                    UpdatePage updatePage = (UpdatePage) JsonUtils.fromJson(vcsLogEntity.action, ActionType.UPDATE_PAGE);
                    logger.debug(TAG, "getDocumentPagePathListRev: Updating " + updatePage.getFileName() + " at " + updatePage.getIndex());
                    fileNameList.set(updatePage.getIndex(), updatePage.getFileName());
                } else if (vcsLogEntity.actionType.equals(ActionType.DELETE_PAGE.getValue())){
                    DeletePage deletePage = (DeletePage) JsonUtils.fromJson(vcsLogEntity.action, ActionType.DELETE_PAGE);
                    logger.debug(TAG, "getDocumentPagePathListRev: Deleting " + deletePage.getIndex());
                    fileNameList.remove(deletePage.getIndex());
                } else if (vcsLogEntity.actionType.equals(ActionType.CREATE_DOCUMENT.getValue())) {
                    // Ignore
                    logger.debug(TAG, "getDocumentPagePathListRev: Ignored action type: " + vcsLogEntity.actionType);
                } else {
                    logger.error(TAG, "getDocumentPagePathListRev: Unknown action type");
                }
            }

            return fileNameList;
        });
    }

    @Override
    public void printLog() {
        logger.debug(TAG, "printLog");
        database.vcsLogDao().findAll().forEach(vcsLog -> {
            logger.debug(TAG, vcsLog.id);
            logger.debug(TAG, vcsLog.documentId + " " + vcsLog.branchName);
            logger.debug(TAG, vcsLog.action);
        });
    }

    @Override
    public void printRev() {
        logger.debug(TAG, "printRev");
        database.vcsRevDao().findAll().forEach(vcsRev -> {
            logger.debug(TAG, vcsRev.id);
            logger.debug(TAG, vcsRev.documentId + " " + vcsRev.branchName);
            logger.debug(TAG, vcsRev.commitMessage);
            logger.debug(TAG, vcsRev.logIds.toString());
        });
    }
}
