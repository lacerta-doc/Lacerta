package one.nem.lacerta.model;

import java.util.Date;

public class VcsLogModel {

    String id;
    String documentId;
    String branchName;
    String action;
    Date createdAt;

    public VcsLogModel(String id, String documentId, String branchName, String action, Date createdAt) {
        this.id = id;
        this.documentId = documentId;
        this.branchName = branchName;
        this.action = action;
        this.createdAt = createdAt;
    }

    // Empty constructor
    public VcsLogModel() {
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

    public String getAction() {
        return action;
    }

    public Date getCreatedAt() {
        return createdAt;
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

    public void setAction(String action) {
        this.action = action;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
