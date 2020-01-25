public class autoGenPass {
    final static char[] abc = {'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c',
            'v','b','n','m','q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v',
            'b','n','m'};
    final static char[] number = {1,2,3,4,5,6,7,8,9,0,'{','}','|','<','>','?',':','\'',',','\\','\"','[',']','=','-',')','(','*',
            '&','^','%','$','#','@','!','+',')',';','.','|','/','й','ц','у','к','е','н','г','ш','щ','з','х','ъ','ф','ы',
            'в','а','п','р','о','л','д','ж','э','я','ч','с','м','и','т','ь','б','ю','Й','Ц','У','К','Е','Н','Г','Ш','Щ',
            'З','Х','Ъ','Ф','Ы','В','А','П','Р','О','Л','Д','Ж','Э','Я','Ч','С','М','И','Т','Ь','Б','Ю'
    };

    public static void main(String[] args) {
        System.out.println((Math.pow(10d,-6d) * 100d/15d));

        System.out.println(autoGenPass(6));
    }

    public static String autoGenPass(int lengthPass){
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < lengthPass; i++) {
            password.append(abc[(int) (Math.random() * abc.length)]);
        }
        return password.toString();
    }
}
