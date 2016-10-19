
package DES;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Naufal A on 08/10/2016.
 */

public class DesEncrypter {
    private String key;
    private String plaintext;
    private int[] listShift = new int[] {0,1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
    private int[] matrixIp = new int[]{58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,
            62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,
            59,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7};
    private int[] matrixIvIp = {
            40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25
    };
    public int[] matrixPcSatu = new int[]{57, 49, 41, 33, 25, 17, 9, 1,
            58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39,
            31, 23, 15, 7,  62, 54, 46, 38, 30, 22, 14, 6,  61, 53, 45, 37, 29, 21, 13, 5,  28, 20, 12, 4};
    public int[] matrixPcDua = {
            14, 17, 11, 24, 1,  5,
            3,  28, 15, 6,  21, 10,
            23, 19, 12, 4,  26, 8,
            16, 7,  27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };
    private int[] matrixExpand = new int[] {32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,
            16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,1};
    public List<int[][]> sBoxes = new ArrayList<>();

    private int [] pBox={16 ,7 ,20 ,21,29, 12, 28, 17,1, 15, 23, 26,5, 18, 31, 10,2 ,8 ,
            24 ,14,32, 27, 3, 9,19, 13, 30, 6,22, 11, 4, 25};


    public DesEncrypter(String plaintext)throws UnsupportedEncodingException{
        this.plaintext = plaintext;
    }

    public void initSBoxes(){
        int [][] SBOX1= {{14 ,4 ,13 ,1 ,2 ,15 ,11 ,8 ,3 ,10 ,6 ,12 ,5 ,9 ,0 ,7},
                {0 ,15 ,7 ,4 ,14 ,2 ,13 ,1 ,10 ,6 ,12 ,11 ,9 ,5 ,3 ,8},
                {4 ,1 ,14 ,8 ,13 ,6 ,2 ,11 ,15 ,12 ,9 ,7 ,3 ,10 ,5 ,0},
                {15, 12, 8, 2, 4, 9, 1, 7 ,5, 11, 3, 14, 10, 0, 6, 13}
        };
        int [][] SBOX2= {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12 ,0, 5, 1},
                {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9 ,11, 5},
                {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                {13 ,8 ,10 ,1 ,3 ,15 ,4 ,2 ,11 ,6 ,7 ,12 ,0 ,5 ,14 ,9}
        };

        int [][] SBOX3={{10 ,0 ,9 ,14 ,6 ,3 ,15 ,5 ,1 ,13 ,12 ,7 ,11 ,4 ,2 ,8},
                {13, 7, 0, 9, 3, 4, 6, 10, 2 ,8 ,5 ,14, 12, 11, 15, 1},
                {13, 6, 4, 9, 8 ,15 ,3 ,0, 11, 1, 2, 12, 5, 10, 14, 7},
                {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
        };

        int [][] SBOX4={{7 ,13 ,14 ,3 ,0 ,6 ,9 ,10 ,1 ,2 ,8 ,5 ,11 ,12 ,4 ,15},
                {13 ,8 ,11 ,5, 6 ,15, 0 ,3 ,4 ,7 ,2 ,12 ,1 ,10 ,14 ,9},
                {10, 6, 9, 0, 12, 11, 7 ,13, 15, 1, 3, 14, 5, 2, 8, 4},
                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4 ,5 ,11 ,12, 7, 2, 14}
        };

        int [][] SBOX5={{2 ,12 ,4 ,1 ,7 ,10 ,11 ,6 ,8 ,5 ,3 ,15 ,13 ,0 ,14 ,9},
                {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3 ,9 ,8 ,6},
                {4 ,2, 1, 11, 10, 13, 7 ,8 ,15, 9, 12, 5, 6, 3, 0, 14},
                {11, 8, 12 ,7, 1, 14 ,2 ,13 ,6 ,15, 0 ,9, 10, 4, 5, 3}
        };

        int [][] SBOX6={{12 ,1 ,10 ,15, 9 ,2, 6 ,8 ,0 ,13 ,3 ,4 ,14 ,7 ,5 ,11},
                {10, 15, 4 ,2 ,7 ,12 ,9, 5, 6, 1, 13 ,14 ,0, 11, 3 ,8},
                {9, 14, 15, 5, 2 ,8 ,12, 3, 7 ,0 ,4 ,10, 1, 13 ,11 ,6},
                {4, 3, 2, 12, 9, 5, 15 ,10 ,11, 14, 1, 7, 6, 0, 8, 13}
        };

        int [][] SBOX7={ {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7 ,5 ,10 ,6 ,1},
                {13, 0, 11, 7, 4, 9, 1, 10, 14 ,3 ,5, 12, 2, 15, 8, 6},
                {1, 4, 11, 13, 12, 3, 7 ,14, 10, 15, 6, 8, 0, 5, 9, 2},
                {6, 11, 13, 8 ,1 ,4, 10, 7, 9, 5 ,0 ,15 ,14, 2, 3, 12}
        };
        int [][] SBOX8={ {13, 2, 8, 4, 6, 15, 11, 1 ,10, 9, 3, 14, 5, 0, 12, 7},
                {1, 15, 13, 8 ,10, 3 ,7 ,4 ,12, 5, 6 ,11, 0, 14, 9, 2},
                {7, 11, 4, 1 ,9 ,12 ,14 ,2 ,0 ,6 ,10, 13 ,15 ,3 ,5 ,8},
                {2, 1, 14, 7 ,4 ,10, 8 ,13, 15, 12, 9, 0, 3, 5, 6, 11}
        };
        this.sBoxes.add(0,SBOX1);
        this.sBoxes.add(1,SBOX2);
        this.sBoxes.add(2,SBOX3);
        this.sBoxes.add(3,SBOX4);
        this.sBoxes.add(4,SBOX5);
        this.sBoxes.add(5,SBOX6);
        this.sBoxes.add(6,SBOX7);
        this.sBoxes.add(7,SBOX8);
    }

    //konversi teks ke biner
    public String teksToBiner(String plaintext) throws UnsupportedEncodingException {
        byte[] infoBiner;
        String plainBiner = "";
        infoBiner = plaintext.getBytes("UTF-8");
        for (byte b : infoBiner) {
            plainBiner += String.format("%8s",Integer.toBinaryString(b));
        }
        return plainBiner.replaceAll(" ","0");
    }

    //Membuat Blok biner 64bit
    public List<String> makeBlocks(String plaintextBiner, int length) throws UnsupportedEncodingException {
        //String plaintextBiner = teksToBiner(this.plaintext);
        String plainBinerBlock;
        int index=0;
        List<String> plainBinerBlocks = new ArrayList<String>();
        while (index < plaintextBiner.length()){
            plainBinerBlock = plaintextBiner.substring(index, Math.min(index + length, plaintextBiner.length()));
            if (plainBinerBlock.length()==length) {
                plainBinerBlocks.add(plainBinerBlock);
            }
            else {
                do {
                    plainBinerBlock += "11111111";
                }while (plainBinerBlock.length()<length);
                plainBinerBlocks.add(plainBinerBlock);
            }
            index = index + length;
        }
        return plainBinerBlocks;
    }

    //permutaasi
    public String permutation(String plainBinerBlock, int[] matrix) throws UnsupportedEncodingException {
        int index;
        String plainBinerBlockPermutation = "";
        for (int m = 0; m<matrix.length; m++){
            index = matrix[m];
            plainBinerBlockPermutation = plainBinerBlockPermutation + plainBinerBlock.charAt(index-1);
        }
        return plainBinerBlockPermutation;
    }

    public String keyToBiner(String key){
        return String.format("%64s",new BigInteger(key, 16).toString(2)).replaceAll(" ","0");
    }

    public String getLeftPlainBiner(String plaintextBiner){
        return plaintextBiner.substring(0,32);
    }

    public String getRighPlainBiner(String plaintextBiner){
        return plaintextBiner.substring(32,64);
    }

    public String getLeftKeyBiner(String key){
        return key.substring(0,28);
    }

    public String getRightKeyBiner(String key){
        return key.substring(28,56);
    }

    public String leftShift(String keyBiner, int shift){
        String temp;
        temp = keyBiner.substring(shift)+keyBiner.substring(0,shift);
        return temp;
    }

    public String merge(String leftKeyBiner, String rightLeftBiner){
        return leftKeyBiner + rightLeftBiner;
    }

    public String expand(String key, int[] matrix) throws UnsupportedEncodingException {
        return permutation(key,matrix);
    }

    public String xor(String r, String k){
        char[] temp = r.toCharArray();
        char[] temp2 = k.toCharArray();
        String a = "";
        for (int i=0; i<r.length(); i++){
            a = a + Character.toString(temp[i]).compareTo(Character.toString(temp2[i]));
        }
        return a.replaceAll("-","");
    }

    public String sBox(String keyBiner, List<int[][]> matrix) throws UnsupportedEncodingException {
        List<String> blocks = makeBlocks(keyBiner,6);
        String block = "";
        int row;
        int col;
        String temp;
        String s = "";
        int[][] mx = new int[][]{};
        for (int i=0; i<8; i++){
            block = blocks.get(i);
            mx = matrix.get(i);
            temp = block.substring(0,1) + block.substring(5,6);
            row = Integer.parseInt(temp,2);
            temp = block.substring(1,5);
            col = Integer.parseInt(temp,2);
            s = s + String.format("%4s",Integer.toBinaryString(mx[row][col])).replaceAll(" ","0");
        }
        return s;
    }

    public List<String> encrypt(String plaintext, String key) throws UnsupportedEncodingException {
        plaintext = teksToBiner(plaintext);
        List<String> plaintextBlocks = makeBlocks(plaintext,64); //mengecek tiap blok harus 64bit
        List<String> K = new ArrayList<>();
        List<String> C = new ArrayList<>();
        List<String> D = new ArrayList<>();
        List<String> L = new ArrayList<>();
        List<String> R = new ArrayList<>();
        List<String> A = new ArrayList<>();
        List<String> B = new ArrayList<>();
        List<String> PB = new ArrayList<>();
        List<String> chiper = new ArrayList<>();

        String keyTemp = key;
        String leftPlaintext = "";
        String rightPlaintext = "";
        String k = "";
        String a = "";
        String b = "";
        initSBoxes();

        for (String plaintextBlock : plaintextBlocks){ //tiap blok 64bit
            PB.add("");
            B.add("");
            A.add("");
            plaintextBlock = permutation(plaintextBlock,matrixIp);
            leftPlaintext = getLeftPlainBiner(plaintextBlock);
            rightPlaintext = getRighPlainBiner(plaintextBlock);
            L.add(leftPlaintext); //L0
            R.add(rightPlaintext); //R0

            System.out.println("Key : " + keyToBiner(key));
            key = keyToBiner(key);
            K.add(key); //K0
            key = permutation(key, matrixPcSatu); //ubah ke biner dan di kompress ke 56bit
            System.out.println("Key -> PC-1 : " + key);
            System.out.println("-------------------");
            C.add(getLeftKeyBiner(key));//C0
            D.add(getRightKeyBiner(key));//D0
            for (int n=0; n<16; n++){
                //internalKey = leftShift(getLeftKeyBiner(key), listShift[n]) + leftShift(getRightKeyBiner(key), n);//geser bit sebanyak n kali
                C.add(leftShift(C.get(n), listShift[n+1]));
                D.add(leftShift(D.get(n), listShift[n+1]));

                key = C.get(n+1)+D.get(n+1);

                k = permutation(key, matrixPcDua); //permutasi dengan matriks PCdua atau K
                K.add(k);

                a = xor(K.get(n+1),expand(R.get(n),matrixExpand));
                A.add(a);
                b = sBox(A.get(n+1),this.sBoxes);
                B.add(b); //B1
                b = permutation(b,pBox);
                PB.add(b); //PB1
                leftPlaintext = R.get(n);
                L.add(leftPlaintext);
                rightPlaintext = xor(L.get(n),PB.get(n+1));
                R.add(rightPlaintext);
            }
//            for (int i=0; i<16; i++){
//                System.out.println("C"+i+" : "+C.get(i));
//                System.out.println("D"+i+" : "+D.get(i));;
//                System.out.println("C"+i+"D"+i+" : "+C.get(i)+D.get(i));
//                System.out.println("");
//            }
//            System.out.println("-------------------");
//            int n = 0;
//            for (String y: K){
//                System.out.println("K"+n+" : "+y);
//                n++;
//            }
//            System.out.println("-------------------");
//            n=0;
//            for (String y: A){
//                System.out.println("A"+n+" : "+y);
//                n++;
//            }
//            System.out.println("-------------------");
//            n=0;
//            for (String y: B){
//                System.out.println("B"+n+" : "+y);
//                n++;
//            }
//            System.out.println("-------------------");
//            n=0;
//            for (String y: PB){
//                System.out.println("P(B"+n+")"+" : "+y);
//                n++;
//            }
//            System.out.println("-------------------");
//            for (int i=0; i<=16; i++){
//                System.out.println("L"+i+" : "+L.get(i));
//                System.out.println("R"+i+" : "+R.get(i));
//                System.out.println("L"+i+"R"+i+" : "+L.get(i)+R.get(i));
//                System.out.println("");
//            }
            chiper.add(permutation(L.get(16)+R.get(16),matrixIvIp));
            K.clear();
            C.clear();
            D.clear();
            L.clear();
            R.clear();
            A.clear();
            B.clear();
            PB.clear();
            key = keyTemp;
        }
        return chiper;
    }

    public String decrypt(String chiperText, String key){

    }
}
