package one.nem.lacerta.model.document;

import java.util.ArrayList;
import one.nem.lacerta.model.document.tag.DocumentTag;
import java.util.Date;
import java.util.List;

public class DocumentMeta {

        String id;
        String title;
        Date updatedAt;
        Date createdAt;
        List<DocumentTag> tags;

        // Getter

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public List<DocumentTag> getTags() {
            return tags;
        }

        // Setter

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public void setTags(List<DocumentTag> tags) {
            this.tags = tags;
        }
}
