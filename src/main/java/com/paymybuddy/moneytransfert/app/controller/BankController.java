package com.paymybuddy.moneytransfert.app.controller;

import com.paymybuddy.moneytransfert.app.service.IBanckServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BankController {

    private static final Logger logger = LogManager.getLogger("BankController");

    @Autowired
    IBanckServices banckServices;

    @PostMapping("/bank/operation")
    public String bankOperation(Model model, String myAccountString, String accountIdString, int transactionAmount, String transactionSourceLabel, String operationType) {

        logger.info("operationType : " + operationType);
        logger.info("myAccountString : " + myAccountString);
        logger.info("accountIdString : " + accountIdString);
        logger.info("transactionAmount : " + transactionAmount);
        logger.info("transactionSourceLabel : " + transactionSourceLabel);

        int accountId = 0;
        int myAccount = 0;

        myAccount = Integer.parseInt(myAccountString);
        logger.info("myAccount : " + myAccount);

        if (! accountIdString.equals(null) && ! accountIdString.equals("")){
            accountId = Integer.parseInt(accountIdString);
            logger.info("accountId : " + accountId);
        }


        try {

            if (operationType.equals("Vers")) {
                logger.info("operationType : " + operationType);

                banckServices.verser(myAccount, transactionAmount, transactionSourceLabel);
            }

            if (operationType.equals("Retr")) {
                logger.info("operationType : " + operationType);

                banckServices.retirer(model, myAccount, transactionAmount, transactionSourceLabel);
            }

            if (operationType.equals("Vire")) {
                logger.info("operationType : " + operationType);
                banckServices.virer(model, myAccount, accountId, transactionAmount, transactionSourceLabel);
            }
        } catch (Exception e) {
            logger.info("e.getMessage() : "+e.getMessage());
            if(model != null)
                model.addAttribute("error", e);
            return "redirect:/transaction"+"?error="+e.getMessage();
        }
        return "redirect:/transaction";
    }

}
