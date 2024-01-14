package one.nem.lacerta.vcs.impl;

import java.util.UUID;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import one.nem.lacerta.source.database.LacertaDatabase;
import one.nem.lacerta.source.database.entity.VcsLogEntity;
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

    }

    @Override
    public void printLog() {
        logger.debug(TAG, "printLog");
        database.vcsLogDao().findAll().forEach(vcsLog -> {
            logger.debug(TAG, vcsLog.id);
        });
    }
}
