class Prime_no{
    public static void main(String args[]){
        int[] numbers={19,49};
        for(int num:numbers){
            boolean isPrime=true;
            if(num<=1){
                isPrime=false;
            }
            else{
                for(int i=2;i<=Math.sqrt(num);i++)
                {
                    if(num%i==0){
                        isPrime=false;
                        break;
                    }
                }
            }
             if(isPrime==true){
                System.out.println(num+"is prime number");
            }
            else{
                System.out.println(num+"is not prime number");
            }
            }
           
        }
    }