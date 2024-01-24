package one.nem.lacerta.vcs.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import one.nem.lacerta.model.VcsLogModel;
import one.nem.lacerta.model.VcsRevModel;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.source.database.LacertaDatabase;
import one.nem.lacerta.source.database.entity.VcsLogEntity;
import one.nem.lacerta.source.database.entity.VcsRevEntity;
import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.vcs.ActionType;
import one.nem.lacerta.vcs.LacertaVcs;
import one.nem.lacerta.vcs.internal.JsonUtils;
import one.nem.lacerta.vcs.model.action.InsertPage;

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
        vcsLogEntity.action = JsonUtils.toJson(insertPage);
        database.vcsLogDao().insert(vcsLogEntity);
    }

    @Override
    public void deletePage(int index) {

    }

    @Override
    public void createDocument(String documentId) {
        logger.debug(TAG, "createDocument");

        VcsLogEntity vcsLogEntity = new VcsLogEntity();
        vcsLogEntity.id = UUID.randomUUID().toString();
        vcsLogEntity.documentId = documentId;
        vcsLogEntity.branchName = "master";
        vcsLogEntity.createdAt = new java.util.Date();
        vcsLogEntity.action = "ph-createDocument";
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
            logger.debug(TAG, "getLogHistoryAtRev");
            ArrayList<VcsLogModel> vcsLogModels = new ArrayList<>();

            VcsRevEntity vcsRevEntity = database.vcsRevDao().findById(revId);
            vcsRevEntity.logIds.forEach(logId -> {
                VcsLogEntity vcsLogEntity = database.vcsLogDao().findById(logId);
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

    @Override
    public CompletableFuture<ArrayList<DocumentDetail>> getDocumentDetailAtRev(String revId) {
        return null;
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
