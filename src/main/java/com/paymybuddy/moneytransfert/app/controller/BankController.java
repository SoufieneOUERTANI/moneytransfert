package com.paymybuddy.moneytransfert.app.controller;

import com.paymybuddy.moneytransfert.app.model.Transaction;
import com.paymybuddy.moneytransfert.app.service.IBanckServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.OperationType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BankController {

    private static final Logger logger = LogManager.getLogger("BankController");

    @Autowired
    IBanckServices banckServices;

    @PostMapping("/bank/operation")
    public String bankOperation(Model model, String myAccount, String accountId, int transactionAmount, String transactionSourceLabel,String OperationType){
        logger.info("OperationType : "+ OperationType);
        logger.info("myAccount : "+ myAccount);
        logger.info("accountId : "+accountId);
        logger.info("transactionAmount : "+transactionAmount);
        logger.info("transactionSourceLabel : "+transactionSourceLabel);

        if (OperationType.equals("Vers")){
            logger.info("OperationType : "+ OperationType);

            banckServices.verser(myAccount, transactionAmount, transactionSourceLabel);
        }

        if (OperationType.equals("Retr")){
            logger.info("OperationType : "+ OperationType);

            banckServices.retirer(myAccount, transactionAmount, transactionSourceLabel);
        }

        if (OperationType.equals("Vire")){
            logger.info("OperationType : "+ OperationType);

            banckServices.virer(myAccount, accountId,transactionAmount, transactionSourceLabel);
        }
        return "redirect:/";
    }

}
