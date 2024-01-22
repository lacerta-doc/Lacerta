package one.nem.lacerta.model;

import java.util.ArrayList;
import java.util.Date;

public class VcsRevModel {

    String id;

    String documentId;

    String branchName;

    String commitMessage;

    Date createdAt;

    ArrayList<String> logIds;

    public VcsRevModel(String id, String documentId, String branchName, String commitMessage, Date createdAt, ArrayList<String> logIds) {
        this.id = id;
        this.documentId = documentId;
        this.branchName = branchName;
        this.commitMessage = commitMessage;
        this.createdAt = createdAt;
        this.logIds = logIds;
    }

    // Empty constructor
    public VcsRevModel() {
    }

    public String getId() {
        return id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ArrayList<String> getLogIds() {
        return logIds;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setLogIds(ArrayList<String> logIds) {
        this.logIds = logIds;
    }
}
