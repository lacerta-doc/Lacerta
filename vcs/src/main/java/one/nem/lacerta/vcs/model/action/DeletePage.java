package one.nem.lacerta.vcs.model.action;

import one.nem.lacerta.vcs.model.action.common.ActionBase;

public class DeletePage extends ActionBase {

        private int index;

        public DeletePage() {
        }

        public DeletePage(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
}
