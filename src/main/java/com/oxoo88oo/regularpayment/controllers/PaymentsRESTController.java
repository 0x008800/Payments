package com.oxoo88oo.regularpayment.controllers;

import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.validation.Validation;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController("/")
public class PaymentsRESTController {

/* Методы CRUD платежей
Create Update Delete
Метод получения платежа по ID
Метод получения списка платежей по ИНН
Метод получения списка платежей по Получателю (ОКПО) */


    //создание платежа
    @PostMapping("/payment/create")
    public boolean createPayment(@RequestBody Payment payment)throws ImpossibleException{

           return Validation.createPayment(payment);

        //return true;
    }

    //todo
    //обновление платежа
    @PutMapping("/payment/update/{payment}")
    public boolean updatePayment(@PathVariable("payment") Payment payment) throws ImpossibleException {
        return Validation.updatePayment(payment);
    }

    //удаление платежа
    // curl -X DELETE localhost:8080/payment/delete/1
    @DeleteMapping("/payment/delete/{id}")
    public boolean deletePayment(@PathVariable("id") String id)throws ImpossibleException{
        return Validation.deletePaymentByID(id);

    }

    //инфо о платеже по айди
    @GetMapping("/payment/info/{id}")
    public Payment getPaymentInfo(@PathVariable String id) throws ImpossibleException {
        return Validation.getPaymentInfo(id);
    }


    //инфо о платежах по инн
    @GetMapping("/payment/info/inn/{inn}")
    public List<Payment> paymentsInfoByINN(@PathVariable String inn) throws ImpossibleException {
        return Validation.getPaymentsInfoByINN(inn);
    }

    //инфо о платежах по окпо получателя
    @GetMapping("/payment/info/okpo/{okpo}")
    public List<Payment> paymentsInfoByOKPO(@PathVariable String okpo) throws SQLException, ImpossibleException {
        return Validation.getPaymentsInfoByOKPO(okpo);
    }
//---------------------------------------------------------------------
    /*
    Методы CRUD проводок
    Create Update Delete
    Метод получения проводки по ID
    Метод получения списка проводок по платежу
*/

//создание проводки
@PostMapping("/provodka/create")
public boolean createProvodka(@RequestBody Provodka provodka) throws ImpossibleException {
    return Validation.createProvodka(provodka);
}


    //update проводки
    @PutMapping("/provodka/update/{id}")
    public boolean updateProvodka(@RequestBody Provodka provodka) throws ImpossibleException {
        return Validation.updateProvodka(provodka);
    }


    //delete проводки
    @DeleteMapping("/provodka/delete/{id}")
    public boolean deleteProvodka(@PathVariable String id) throws ImpossibleException {
        return Validation.deleteProvodka(id);
    }

    //получение проводки по айди
    @GetMapping("/provodka/info/{id}")
    public Provodka ProvodkaByID(@PathVariable("id") String id) throws ImpossibleException {
                return Validation.getProvodkaByID(id);
    }

    //получение списка проводок по айди платежа
    @GetMapping("/provodka/info/bypaymentid/{id}")
    public List<Provodka> getProvodkiByPaymentId(@PathVariable("id") String id) throws ImpossibleException {
        return Validation.getInfoByProvodkiOfPayment(id);
    }

    //-----------------------------------------------------------------------

    //todo
    //списание платежа
    //создание проводки по платежу
    @GetMapping("/createprovodka/{idofpayment}")
    public boolean usePayment(@PathVariable("idofpayment") String idOfPayment) throws ImpossibleException {
        return Validation.usePayment(idOfPayment);
    }


    //todo
    //проверка необходимости списания
    // curl localhost:8080/isneedtopay/255
    @GetMapping("/isneedtopay/{id}")
    public boolean isNeeded(@PathVariable("id") String id) throws ImpossibleException {
        return Validation.isNeedToPay(id);
    }

//todo
//Сторнировка проводки
// curl localhost:8080//255
@GetMapping("/provodka/stornirovka/{id}")
public boolean stornirovkaProvodki(@PathVariable("id") String id) throws ImpossibleException {
    return Validation.stornirovkaProvodki(id);
}


//-----------------------------------------------------------------
//получение данных

    /*дублирующий метод "инфо о платежах по инн"

    // получение всех платежей по отправителю(по ИНН)
    @GetMapping("/getinfo/sender/{inn}")
    public @ResponseBody List<Payment> getInfoBySender(@PathVariable("inn") String inn) throws SQLException {

    List<Payment> list =  Validation.getAllSendersPaymentsByINN(inn);
    //list.forEach(item -> System.out.println(item.getId()));
    return list;
}*/

//получение всех платежей по получателю(по расчётному счёту)
@GetMapping("/receiver/info/{accaunt}")
public @ResponseBody List<Payment> getInfoReceiverByaccaunt(@PathVariable("accaunt") String accaunt) throws ImpossibleException {
    return Validation.getAllReceiverPaymentsByAccaunt(accaunt);
}

//todo delete
//получение истории списания платежа
@GetMapping("/payment/provodki/{paymentID}")
public List<Provodka> getInfoByProvodkiOfPayment(@PathVariable("paymentID") String id) throws ImpossibleException {
    return Validation.getInfoByProvodkiOfPayment(id);
}


//-------------------------------------------------------------------








}
