package com.oxoo88oo.regularpayment.db;

import com.oxoo88oo.regularpayment.DAO.DAOPayment;
import com.oxoo88oo.regularpayment.DAO.DAOProvodki;
import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.entities.Status;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DBConnector {

    private static Logger logger = LoggerFactory.getLogger(DBConnector.class);

    private static final String dbError = "DB ERROR";
    //private static Connection connection = null;
    //private static PreparedStatement ps = null;
    //private static Connection conn = null;
    public static PreparedStatement getPrepStmt(String query){
        //PreparedStatement ps = null;
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
            PreparedStatement ps = conn.prepareStatement(query);){
                return ps;
        }catch (SQLException e){
            logger.debug("GET PREP STMT EXCEPT");
            e.printStackTrace();
            return null;
        }

        // connection =  DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db/payments", "sa", "");
    }

    //создание платежа
    public static boolean createPayment(Payment payment)throws ImpossibleException {
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(insertIntoPayments)){

            DAOPayment.setPayment(payment, ps);

        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return true;
    }

    public static boolean createProvodka(Provodka provodka) throws ImpossibleException{
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(insertIntoProvodki)) {


            ps.setLong(1, provodka.getIdOfPayment());
            ps.setLong(2, System.currentTimeMillis());
            ps.setBigDecimal(3, provodka.getCount());
            ps.setString(4, provodka.getStatus().toString());
            ps.addBatch();

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return true;

    }


    public static boolean create(){

        return true;
    }
    public static boolean updatePayment(Payment payment) {
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(updatePaymentQuery)){

            return tmp(payment, ps);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletePayment(long id) throws ImpossibleException {
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(insertIntoPayments)){

            ps.setLong(1, id);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return true;
    }

    public static Payment getPaymentById(Long id) throws ImpossibleException {
        Payment payment = new Payment();
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(getPaymentByIdQuery)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                payment.setId(rs.getLong("id"));
                payment.setNameOfSender(rs.getString(2));
                payment.setINNofSender(rs.getLong(3));
                payment.setNumberOfCardOfSender(rs.getLong(4));
                payment.setAccauntOfReceiver(rs.getLong(5));
                payment.setMFOofReceiver(rs.getLong(6));
                payment.setOKPOofReceiver(rs.getLong(7));
                payment.setNameOfReceiver(rs.getString(8));
                payment.setPeriod(rs.getLong(9));
                payment.setCount(rs.getBigDecimal(10));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return payment;

    }

    public static boolean isNeededToPay(long id) throws ImpossibleException{
        long currentTime = System.currentTimeMillis();
        long lastTime = 0;
        long period = 0;

        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement("SELECT MAX(time) FROM PROVODKI WHERE id_of_payment = ?")){

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                lastTime = rs.getLong(1);
            }

            PreparedStatement ps1 = conn.prepareStatement("SELECT period FROM PAYMENTS WHERE id = ?");
            ps1.setLong(1, id);
            rs = ps1.executeQuery();
            while (rs.next()){
                period = rs.getLong(1);
            }

            //если мы не нулевые значения периода и времени последн проводки
            //и если если между настоящим временем и временем последней
            //проводки прошло больше чем период = платёж нуждается в проводке
            if(lastTime > 0 && period > 0 && currentTime - lastTime > period){
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);

        }
        //иначе - не нуждается
        return false;
    }

    //вставка в таблицу новой проводки
    public static boolean usePayment(long id, BigDecimal count, Status status) throws ImpossibleException {

        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(insertIntoProvodki)) {

            ps.setLong(1, id);
            ps.setDate(2, new Date(System.currentTimeMillis()));
            ps.setBigDecimal(3, count);
            ps.setString(4, status.toString());
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return true;

    }

    public static List<Payment> getAllSendersPaymentsByINN(Long inn) throws ImpossibleException {
        Payment payment;
        List<Payment> payments = new ArrayList<>();

        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(getAllPaymentsByINNQuery)) {


            ps.setLong(1, inn);
            ResultSet resultSet = ps.executeQuery();



            while (resultSet.next()) {
                payment = new Payment();
                payment.setId(resultSet.getInt(1));
                //System.out.println(resultSet.getInt(1));
                payment.setNameOfSender(resultSet.getString(2));
                payment.setINNofSender(resultSet.getLong(3));
                payment.setNumberOfCardOfSender(resultSet.getLong(4));
                payment.setAccauntOfReceiver(resultSet.getLong(5));
                payment.setMFOofReceiver(resultSet.getLong(6));
                payment.setOKPOofReceiver(resultSet.getLong(7));
                payment.setNameOfReceiver(resultSet.getString(8));
                payment.setPeriod(resultSet.getLong(9));
                payment.setCount(resultSet.getBigDecimal(10));

                payments.add(payment);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);

        }

        return payments;
    }

    public static List<Payment> getAllPaymentsByOKPO(Long okpo)throws ImpossibleException {
        Payment payment;
        List<Payment> payments = new ArrayList<>();

        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(getAllPaymentsByOKPOQuery)) {


            ps.setLong(1, okpo);
            ResultSet resultSet = ps.executeQuery();



            while (resultSet.next()) {
                payment = new Payment();
                payment.setId(resultSet.getInt(1));
                //System.out.println(resultSet.getInt(1));
                payment.setNameOfSender(resultSet.getString(2));
                payment.setINNofSender(resultSet.getLong(3));
                payment.setNumberOfCardOfSender(resultSet.getLong(4));
                payment.setAccauntOfReceiver(resultSet.getLong(5));
                payment.setMFOofReceiver(resultSet.getLong(6));
                payment.setOKPOofReceiver(resultSet.getLong(7));
                payment.setNameOfReceiver(resultSet.getString(8));
                payment.setPeriod(resultSet.getLong(9));
                payment.setCount(resultSet.getBigDecimal(10));

                payments.add(payment);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);

        }

        return payments;
    }

    public static boolean createAllTables()throws ImpossibleException{

        //todo
        try{
            Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");

            PreparedStatement ps = conn.prepareStatement(createPaymentTableQuery);
            ps.executeUpdate();

            ps = conn.prepareStatement(createProvodkiTableQuery);
            ps.executeUpdate();

            ps = conn.prepareStatement(createMoneyOfSendersTableQuery);
            ps.executeUpdate();

            ps = conn.prepareStatement(createMoneyOfReceiversTableQuery);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return true;
    }

    public static boolean dropAllTables()throws ImpossibleException {

        //todo
        try{
        Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
        PreparedStatement ps = conn.prepareStatement(dropAllTablesQuery);
        ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return true;
    }

    public static List<Payment> getAllReceiverPaymentsByAccaunt(long accReceiver) throws ImpossibleException {

        Payment payment;
        List<Payment> payments = new ArrayList<>();
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(getAllReceiverPaymentsByAccauntQuery)) {


            ps.setLong(1, accReceiver);
            ResultSet resultSet =
                    ps.executeQuery();



            while (resultSet.next()) {
                payment = new Payment();
                payment.setId(resultSet.getInt(1));
                //System.out.println(resultSet.getInt(1));
                payment.setNameOfSender(resultSet.getString(2));
                payment.setINNofSender(resultSet.getLong(3));
                payment.setNumberOfCardOfSender(resultSet.getLong(4));
                payment.setAccauntOfReceiver(resultSet.getLong(5));
                payment.setMFOofReceiver(resultSet.getLong(6));
                payment.setOKPOofReceiver(resultSet.getLong(7));
                payment.setNameOfReceiver(resultSet.getString(8));
                payment.setPeriod(resultSet.getLong(9));
                payment.setCount(resultSet.getBigDecimal(10));

                payments.add(payment);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }

        return payments;
    }

    public static List<Provodka> getInfoByProvodkiOfPayment(long id) throws ImpossibleException {
        Provodka provodka;
        List<Provodka> provodkas = new ArrayList<>();
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(getAllProvodkaByPaymentIDQuery)) {



            ps.setLong(1, id);
            ResultSet resultSet =
                    ps.executeQuery();



            while (resultSet.next()) {
                provodka = new Provodka();

                provodka.setId(resultSet.getInt(1));
                provodka.setIdOfPayment(resultSet.getInt(2));
                provodka.setTime(resultSet.getLong(3));
                provodka.setCount(resultSet.getBigDecimal(4));
                provodka.setStatus(Status.valueOf(resultSet.getString(5)));

                provodkas.add(provodka);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }

        return provodkas;
    }

    public static List<Payment> getAllPayments() throws ImpossibleException {
        List<Payment> payments = new ArrayList<>();
        Payment payment;

        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(getAllPaymentsQuery)) {

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                payment = new Payment();
                payment.setId(resultSet.getInt(1));
                payment.setNameOfSender(resultSet.getString(2));
                payment.setINNofSender(resultSet.getLong(3));
                payment.setNumberOfCardOfSender(resultSet.getLong(4));
                payment.setAccauntOfReceiver(resultSet.getLong(5));
                payment.setMFOofReceiver(resultSet.getLong(6));
                payment.setOKPOofReceiver(resultSet.getLong(7));
                payment.setNameOfReceiver(resultSet.getString(8));
                payment.setPeriod(resultSet.getLong(9));
                payment.setCount(resultSet.getBigDecimal(10));

                payments.add(payment);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }

        return payments;

    }

    public static List<Provodka> getAllProvodki() throws ImpossibleException {

        Provodka provodka;
        List<Provodka> provodkas = new ArrayList<>();

        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(getAllProvodkiQuery)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
            provodka  = new Provodka();

            provodka.setId(rs.getInt(1));
            provodka.setIdOfPayment(rs.getInt(2));
            provodka.setTime(rs.getLong(3));
            provodka.setCount(rs.getBigDecimal(4));
            provodka.setStatus(Status.valueOf(rs.getString(5)));

            provodkas.add(provodka);
        }
        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return provodkas;
    }

    public static boolean writeAllProvodki(List<Provodka> retAllProvodokWaitingFor) throws ImpossibleException {

        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(insertIntoProvodki)) {

            for (Provodka provodka : retAllProvodokWaitingFor) {
                ps.setLong(1, provodka.getIdOfPayment());
                ps.setLong(2, System.currentTimeMillis());
                ps.setBigDecimal(3, provodka.getCount());
                ps.setString(4, provodka.getStatus().toString());
                ps.addBatch();
            }
            ps.executeBatch();

        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return true;
    }




    public static boolean updateProvodka(Provodka provodka)throws ImpossibleException {
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(updateProvodkaQuery)) {


            DAOProvodki.setProvodka(provodka, ps);

        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return true;
    }

    public static boolean deleteProvodka(Long id)throws ImpossibleException {
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(deleteProvodkaQuery)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return true;
    }

    public static Provodka getProvodkaByID(Long id) throws ImpossibleException{

        Provodka provodka = new Provodka();

        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(getProvodkaByIDQuery)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                provodka  = new Provodka();

                provodka.setId(rs.getInt(1));
                provodka.setIdOfPayment(rs.getInt(2));
                provodka.setTime(rs.getLong(3));
                provodka.setCount(rs.getBigDecimal(4));
                provodka.setStatus(Status.valueOf(rs.getString(5)));

            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return provodka;
    }

    public static boolean stornirovkaProvodki(Long id)throws ImpossibleException {
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(updateStatusProvodkaQuery)) {

            ps.setString(1, Status.STORNIRE.toString());
            ps.setLong(2, id);

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException(dbError);
        }
        return true;
    }
}
