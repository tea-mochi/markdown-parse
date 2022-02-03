class test{
    public static void main(String[] args){
        String returnString = "\nabcdefg.com\n";
        System.out.println(returnString);
            if(returnString.indexOf("\n")==0){
                returnString = returnString.substring(1);
            }
            if(returnString.indexOf("\n")==returnString.length()-1){
                returnString = returnString.substring(0, returnString.length()-1);
            }
        System.out.println(returnString);
    }
}