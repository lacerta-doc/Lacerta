package one.nem.lacerta.vcs.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import one.nem.lacerta.source.database.LacertaDatabase;
import one.nem.lacerta.source.database.entity.VcsLogEntity;
import one.nem.lacerta.source.database.entity.VcsRevEntity;
import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.vcs.LacertaVcs;

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
        VcsLogEntity vcsLogEntity = new VcsLogEntity();
        vcsLogEntity.id = UUID.randomUUID().toString();
        vcsLogEntity.documentId = documentId;
        vcsLogEntity.branchName = "master";
        vcsLogEntity.createdAt = new java.util.Date();
        vcsLogEntity.action = "placeholder";
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
        vcsLogEntity.action = "placeholder";
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
    }

    @Override
    public void printLog() {
        logger.debug(TAG, "printLog");
        database.vcsLogDao().findAll().forEach(vcsLog -> {
            logger.debug(TAG, vcsLog.id);
        });
    }
}
