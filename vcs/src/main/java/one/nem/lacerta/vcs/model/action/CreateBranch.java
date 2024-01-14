package one.nem.lacerta.vcs.model.action;

public class CreateBranch {

    private String branchName;
    private String parentBranchName;

    public CreateBranch() {
    }

    public CreateBranch(String branchName, String parentBranchName) {
        this.branchName = branchName;
        this.parentBranchName = parentBranchName;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getParentBranchName() {
        return parentBranchName;
    }
}
