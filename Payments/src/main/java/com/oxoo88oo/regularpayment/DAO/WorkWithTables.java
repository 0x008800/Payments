package com.oxoo88oo.regularpayment.DAO;

public abstract class WorkWithTables {

    final String getByIdQuery = "SELECT * FROM %s WHERE id = ?";
    final String getAllQuery =  "SELECT * FROM %s";
    final String getWhereQuery = "SELECT * FROM %s WHERE %s = ?";

    public final static String insertIntoPayments =
            "INSERT INTO PAYMENTS (nameOfSender, inn_of_sender , namber_of_card_of_sender," +
                    " accaun_of_receiver, mfo_of_receiver, okpo_of_receiver, name_of_receiver," +
                    " period, count) values (?,?,?,?,?,?,?,?,?)";
    public final static String insertIntoProvodki =
            "INSERT INTO PROVODKI(id_of_payment, time, count, status) values(?,?,?,?)";

    public static final String updatePaymentQuery =
            "UPDATE PAYMENTS SET nameOfSender = ?, inn_of_sender = ?," +
                    " namber_of_card_of_sender = ?, accaun_of_receiver = ?," +
                    " MFO_OF_RECEIVER = ?, okpo_of_receiver = ?," +
                    " name_of_receiver = ?, period = ?," +
                    " count = ? WHERE id = ?";

    public static  final String updateProvodkaQuery =
            "UPDATE PROVODKI SET id_of_payment = ?, time = ?, count =?," +
                    " status = ? WHERE id = ?";

    final static String deleteQuery = "DELETE FROM %s WHERE ID = ?";

    public final static String createPaymentTableQuery = "CREATE TABLE IF NOT EXISTS PAYMENTS(id BIGINT auto_increment, nameOfSender VARCHAR(25) ," +
            " inn_of_sender BIGINT, namber_of_card_of_sender BIGINT, accaun_of_receiver BIGINT, MFO_OF_RECEIVER BIGINT," +
            "okpo_of_receiver BIGINT, name_of_receiver varchar(25), period BIGINT, count DECIMAL)";
    private final static String createProvodkiTableQuery =
            "CREATE TABLE IF NOT EXISTS PROVODKI(id INT auto_increment, id_of_payment INT," +
                    " time BIGINT, count DECIMAL , status varchar(15))";
    private final static String createMoneyOfSendersTableQuery =
            "CREATE TABLE IF NOT EXISTS MONEY_OF_SENDERS(id int auto_increment, inn BIGINT, count DECIMAL )";
    private final static String createMoneyOfReceiversTableQuery =
            "CREATE TABLE IF NOT EXISTS MONEY_OF_RECEIVERS(id int auto_increment, accaunt_of_receiver BIGINT, count DECIMAL )";


    final static String dropAllTablesQuery = "DROP ALL OBJECTS";

}
