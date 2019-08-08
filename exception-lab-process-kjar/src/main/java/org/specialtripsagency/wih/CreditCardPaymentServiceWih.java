package org.specialtripsagency.wih;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.process.workitem.core.AbstractLogOrThrowWorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.specialtripsagency.PaymentObject;

public class CreditCardPaymentServiceWih extends AbstractLogOrThrowWorkItemHandler {

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        PaymentObject payment = (PaymentObject) workItem.getParameter("payment");
        if (payment.getCreditCardNumber().startsWith("1234")) {
            handleException(new PaymentErrorException("Error with payment for credit card " + payment.getCreditCardHolderName()));
        }
        payment.setStatus("PAID");
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("paymentResult", payment);
        manager.completeWorkItem(workItem.getId(), results);
    }

    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        // TODO Auto-generated method stub
    }
    
    

}
