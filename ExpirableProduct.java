import java.time.LocalDate;
 class ExpirableProduct extends Product {
     private LocalDate expiryDate;
     public ExpirableProduct ( String name , double price , int quantity , LocalDate expiryDate)
     {
         super(name, price, quantity);
         this.expiryDate = expiryDate;
     }
     public boolean IsExpired()
     {
         return LocalDate.now().isAfter(expiryDate);
     }
     @Override
     public boolean isShippable() {
         return false;
     }
}
