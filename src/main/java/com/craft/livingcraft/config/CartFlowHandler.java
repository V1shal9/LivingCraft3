package com.craft.livingcraft.config;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.craft.livingcraft.model.BillingAddress;
import com.craft.livingcraft.model.MainClass;
import com.craft.livingcraft.model.ShippingAddress;

@Component
public class CartFlowHandler 
{
	
	public MainClass initFlow()
	{
		return new MainClass();
	}
	
	   public String validateBillingAddress(BillingAddress billingAddress,MessageContext messageContext)
	   {
		   
		   String status="success";
		   if(billingAddress.getStreetAddress().isEmpty())
		   {
			   messageContext.addMessage(new MessageBuilder().error().source("streetAddress")
		      .defaultText("*Address must be filled for succesfull shopping").build());
			   status="failure";
		   }
		   if(billingAddress.getPostalCode().isEmpty())
		   {
			   messageContext.addMessage(new MessageBuilder().error().source("postalCode")
		      .defaultText("*Address must be filled for succesfull shopping").build());
			   status="failure";
		   }
		   else if(billingAddress.getPostalCode().length()!=6)
		   {
			   messageContext.addMessage(new MessageBuilder().error().source("postalCode")
					   .defaultText("*postal code is not valid").build());
			   status="failure";
		   }
		   
		   if(billingAddress.getCity().isEmpty())
		   {
			   messageContext.addMessage(new MessageBuilder().error().source("city")
		      .defaultText("**Address must be filled for succesfull shopping").build());
			   status="failure";
		   }
		   if(billingAddress.getState().isEmpty())
		   {
			   messageContext.addMessage(new MessageBuilder().error().source("state")
		      .defaultText("*Address must be filled for succesfull shopping").build());
			   status="failure";
		   }
		  
		   return status;
	   }
	   
	   public String validateShippingAddress(ShippingAddress shippingAddress,MessageContext messageContext)
	   {
		   
		   String status="success";
		   if(shippingAddress.getStreetAddress().isEmpty())
		   {
			   messageContext.addMessage(new MessageBuilder().error().source("streetAddress")
		      .defaultText("*Address must be filled for succesfull shopping").build());
			   status="failure";
		   }
		   if(shippingAddress.getPostalCode().isEmpty())
		   {
			   messageContext.addMessage(new MessageBuilder().error().source("postalCode")
		      .defaultText("*Address must be filled for succesfull shopping").build());
			   status="failure";
		   }
		   else if(shippingAddress.getPostalCode().length()!=6)
		   {
			   messageContext.addMessage(new MessageBuilder().error().source("postalCode")
					   .defaultText("*postal code is not valid").build());
			   status="failure";
		   }
		   
		   if(shippingAddress.getCity().isEmpty())
		   {
			   messageContext.addMessage(new MessageBuilder().error().source("city")
		      .defaultText("**Address must be filled for succesfull shopping").build());
			   status="failure";
		   }
		   if(shippingAddress.getState().isEmpty())
		   {
			   messageContext.addMessage(new MessageBuilder().error().source("state")
		      .defaultText("*Address must be filled for succesfull shopping").build());
			   status="failure";
		   }
		  
		   return status;
	   }


}
