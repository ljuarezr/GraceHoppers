package com.gracehoppers.jlovas.bookwrm;

/**
 *
 * Implementation of search by Textual query.
 * The constructor takes in a string that represents the query.
 * Contains a getter and setter method for the query string.
 *
 */
public class SimpleSearchCommand {


    private SimpleSearchQuery query;

    public SimpleSearchCommand(String query) {
        super();
        this.query = new SimpleSearchQuery(query);
    }

    public SimpleSearchCommand(String query, String[] fields) {
        super();
        throw new UnsupportedOperationException("Fields not yet implemented.");
    }

    public SimpleSearchQuery getQuery() {
        return query;
    }

    public void setQuery(SimpleSearchQuery query) {
        this.query = query;
    }

    static class SimpleSearchQuery {
        private SimpleSearchQueryString queryString;

        public SimpleSearchQuery(String query) {
            super();
            this.queryString = new SimpleSearchQueryString(query);
        }

        public SimpleSearchQueryString getQueryString() {
            return queryString;
        }

        public void setQueryString(SimpleSearchQueryString queryString) {
            this.queryString = queryString;
        }

        static class SimpleSearchQueryString {
            private String query;

            public SimpleSearchQueryString(String query) {
                super();
                this.query = query;
            }

            public String getQuery() {
                return query;
            }

            public void setQuery(String query) {
                this.query = query;
            }
        }
    }
}
