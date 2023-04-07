package config;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class used for storing all the SQL statements that should be run together
 * within a single transaction. For each, it also specifies if the statement
 * is an update statement or a query.
 */
public class Transaction {

    /* instance fields */
    public LinkedList<TransactionOp> statements;

    /* constructor for class (initializes new linked list) */
    public Transaction() {
        this.statements = new LinkedList<TransactionOp>();
    }

    /* get size of transaction, so # of statements it will run */
    public int size() {
        return this.statements.size();
    }

    /* add new statement to end of transaction */
    public void addStatement(String sql, StatementType type) {
        this.statements.add(new TransactionOp(sql, type));
    }


    /* defines different types of SQL statements allowed in transaction */
    public enum StatementType {
        QUERY,
        UPDATE,
    }

    /**
     * Sub-class used for storing each SQL statement run in the transaction. This
     * class stores the SQL for the statement and specifies whether it is an
     * update statement or a query.
     */
    public class TransactionOp {
        public String sql;
        public StatementType type;

        public TransactionOp(String sql, StatementType type) {
            this.sql = sql;
            this.type = type;
        }
    }
}
