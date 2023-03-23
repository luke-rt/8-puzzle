class hello{
    public static void main(String[] args) {
        int[] a = {2, 4, 5};
        int[] b = a;
        
        
        

        b[1] = 10;
        for(int i=0; i<a.length; ++i){
            System.out.println(a[i]);
        }
    }
}