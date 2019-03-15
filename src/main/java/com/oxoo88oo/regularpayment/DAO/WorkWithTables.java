package com.oxoo88oo.regularpayment.DAO;

public abstract class WorkWithTables {

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

    private String selectQuery = "SELECT * FROM %s WHERE %s = ?";
    String getByIdQuery = "SELECT * FROM %s WHERE id = ?";
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
    public static final String updateStatusProvodkaQuery =
            "UPDATE PROVODKI SET status = ? WHERE id = ?";



    final static String deleteQuery = "DELETE FROM %s WHERE ID = ?";

    private final static String isNeedToPayQuery = "";
    private final static String dropAllTablesQuery = "DROP ALL OBJECTS";
    private final static String getAllLastProvodkiByPaymentsQuery = "SELECT * FROM PROVODKI";

      /*

          private static final String deleteProvodkaQuery = "DELETE FROM PROVODKI WHERE id = ?";
    private final static String deletePaymentQuery = "DELETE FROM PAYMENTS WHERE ID = ?";
        private static final String getProvodkaByIDQuery =
                "SELECT * FROM PROVODKI WHERE ID = ?";
        private final static String getAllPaymentsByINNQuery =
                "SELECT * FROM PAYMENTS WHERE inn_of_sender = ?";
        private final static String getAllPaymentsByOKPOQuery =
                "SELECT * FROM PAYMENTS WHERE okpo_of_receiver = ?";
        private final static String getAllReceiverPaymentsByAccauntQuery =
                "SELECT * FROM PAYMENTS WHERE accaun_of_receiver = ?";
        private final static String getAllPaymentsQuery =
                "SELECT * FROM PAYMENTS";

        private final static String getAllProvodkaByPaymentIDQuery =
                "SELECT * FROM PROVODKI WHERE id_of_payment = ?";
        private final static String getAllProvodkiQuery =
                "SELECT * FROM PROVODKI";
        private final static String getPaymentByIdQuery = "SELECT * FROM PAYMENTS WHERE id = ?";
    */

}
