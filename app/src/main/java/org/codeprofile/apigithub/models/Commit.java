package org.codeprofile.apigithub.models;

public class Commit {
    private String sha;
    private CommitData commit;

    public String getSha() {
        return sha;
    }

    public CommitData getCommit() {
        return commit;
    }

    class CommitData {
        private Author author;
        private String message;

        public Author getAuthor() {
            return author;
        }

        public String getMessage() {
            return message;
        }

        static class Author {
            private String name;
            private String email;
            private String date;

            public String getName() {
                return name;
            }

            public String getEmail() {
                return email;
            }

            public String getDate() {
                return date;
            }
        }
    }
}
