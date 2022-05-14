/*
FURKAN ÖZELGE 14758028780 CMPE325-N ASSIGNMENT-4
 */
package furkanozelge;
import java.net.URL;
import java.util.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class OzelgeRSApp extends JFrame {
    JButton buttonEncrypt,buttonDecrypt;
    JLabel pLab,qLab,totLab,eLab,nLab,dLab,eValueLabel,encrypted,decrypted;
    JTextField getP,getQ,getE,enterMessage;
    int p = 0, q = 0, n = 0, totient = 0, numOfE = 0, d = 0;
    String plaintext = "";

    public OzelgeRSApp() {
        JLabel emptyLabel1 = new JLabel();
        JLabel emptyLabel2 = new JLabel();
        JLabel emptyLabel3 = new JLabel();
        ActValueGetter vLis = new ActValueGetter();
        ActMsgGetter mLis = new ActMsgGetter();
        ActBtnGetter btnLis = new ActBtnGetter();
        TitledBorder bordUp = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Fill in the blank fields and press ENTER! ");
        TitledBorder bordMid = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Type your messages and press ENTER!!");
        TitledBorder bordBot = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Entered Values;");
        bordUp.setTitleFont(bordBot.getTitleFont().deriveFont(Font.BOLD,15f));
        bordMid.setTitleFont(bordBot.getTitleFont().deriveFont(Font.BOLD,15f));
        bordBot.setTitleFont(bordBot.getTitleFont().deriveFont(Font.BOLD,15f));
        setLayout(new BorderLayout(50, 70));
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(2, 2, 20, 20));
        top.setBorder(bordUp);
        JLabel pValueLabel = new JLabel("First prime number (p)");
        getP = new JTextField();
        JLabel qValueLabel = new JLabel("Second prime number (q)");
        getQ = new JTextField();
        eValueLabel = new JLabel("Prime number that not a divisor of " + totient);
        getE = new JTextField();
        getE.setEnabled(false);
        getP.addActionListener(vLis);
        getE.addActionListener(vLis);
        getQ.addActionListener(vLis);
        top.add(getP);
        top.add(getQ);
        top.add(getE);
        top.add(pValueLabel);
        top.add(qValueLabel);
        top.add(eValueLabel);
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(7, 5, 20, 20));
        bottom.setBorder(bordBot);
        pLab = new JLabel("p: " + p);
        qLab = new JLabel("q: " + q);
        totLab = new JLabel("Totient:  " + totient);
        JLabel publicKey = new JLabel("PUBLIC KEY");
        publicKey.setFont(publicKey.getFont().deriveFont(Font.BOLD,15f));
        eLab = new JLabel("e:  " + numOfE);
        nLab = new JLabel("n:  " + n);
        JLabel privateKey = new JLabel("PRIVATE KEY");
        privateKey.setFont(privateKey.getFont().deriveFont(Font.BOLD,15f));
        dLab = new JLabel("d:  " + d);
        //add elements to the bottom panel
        bottom.add(pLab);
        bottom.add(qLab);
        bottom.add(totLab);
        bottom.add(emptyLabel1);
        bottom.add(publicKey);
        bottom.add(emptyLabel2);
        bottom.add(eLab);
        bottom.add(nLab);
        bottom.add(privateKey);
        bottom.add(emptyLabel3);
        bottom.add(dLab);
        JPanel midPanel = new JPanel();
        midPanel.setLayout(new GridLayout(5, 6, 20, 20));
        midPanel.setBorder(bordMid);

        enterMessage = new JTextField();
        encrypted = new JLabel("Encrypted Message:");
        decrypted = new JLabel("Decrypted Message:");
        buttonEncrypt = new JButton("Press for Encrypt Your Message");
        buttonDecrypt = new JButton("Press for Decrypt Your Message");
        enterMessage.setEnabled(false);
        buttonEncrypt.setEnabled(false);
        buttonDecrypt.setEnabled(false);
        enterMessage.addActionListener(mLis);
        buttonEncrypt.addActionListener(btnLis);
        buttonDecrypt.addActionListener(btnLis);
        midPanel.add(enterMessage);
        midPanel.add(encrypted);
        midPanel.add(buttonEncrypt);
        midPanel.add(decrypted);
        midPanel.add(buttonDecrypt);
        add(midPanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.EAST);
        add(top, BorderLayout.SOUTH);
    }
        static int power(int a,int n, int p) {
            int res = 1;
            a = a % p;
            while (n > 0) {
                if ((n & 1) == 1)
                    res = (res * a) % p;
                n = n >> 1; 
                a = (a * a) % p;}
            return res;}
        static boolean isPrime(int n, int k) {
            if (n <= 1 || n == 4) return false;
            if (n <= 3) return true;
            while (k > 0) {
                int a = 2 + (int) (Math.random() % (n - 4));
                if (power(a, n - 1, n) != 1)
                    return false;
                k--;}
            return true;}
    private class ActValueGetter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int tmp;
            int i,j,k,l;
            if (event.getSource() == getQ) {
                tmp = Integer.parseInt(getQ.getText());
                if (isPrime(tmp,20)) {
                    q = tmp;
                    qLab.setText("Calculated q:" + q);}
                getQ.setText(null);}
            else if (event.getSource() == getP) {
                tmp = Integer.parseInt(getP.getText());
                if (isPrime(tmp,20)) {
                    p = tmp;
                    pLab.setText("Calculated p:" + p);}
                else{JOptionPane.showMessageDialog(null, "The number is not prime", "ERROR MESSAGE!", JOptionPane.PLAIN_MESSAGE);}
                getP.setText(null);} 
            else {
                tmp = Integer.parseInt(getE.getText());
                if (!isPrime(tmp,20)) {
                    tmp = 0;
                }
                if (gcd(tmp, totient) != 1) {
                    JOptionPane.showMessageDialog(null, "The number is a divisor of " + totient, "Error",JOptionPane.PLAIN_MESSAGE);
                    tmp = 0;}
                if (tmp != 0) {
                    numOfE = tmp;
                    eLab.setText("Calculated e:" + numOfE);}
                getE.setText(null);
                euc();
            }
            if (q != 0 && p != 0 ) {
                getE.setEnabled(true);
                n = p * q;
                nLab.setText("Calculated n:" + n);
                totient = (p - 1) * (q - 1);
                totLab.setText("Calculated totient:" + totient);
                eValueLabel.setText("Prime number that not a divisor of " + totient);
            }
        }
        private int gcd(int a, int b) {
            if (b == 0) {
                return a;}
            return gcd(b, a % b);}
        private void euc() {
            int s = 0, t = 1, olds = 1, oldt = 0, r = numOfE, oldr = totient;
            while (r != 1) {
                int quotient = oldr / r;
                int tmp = r;
                r = oldr % r;
                oldr = tmp;
                tmp = s;
                s = olds - quotient * s;
                olds = tmp;
                tmp = t;
                t = oldt - quotient * t;
                oldt = tmp;
            }
            if (t < 0) {
                d = totient + t;
            } else {
                d = t;
            }
            dLab.setText("Calculated d:" + d);
            enterMessage.setEnabled(true);
        }
    }
    private class ActMsgGetter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            plaintext = enterMessage.getText();
            buttonEncrypt.setEnabled(true);
        }
    }
    private class ActBtnGetter implements ActionListener {
        int[] cipherTextArray;
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonEncrypt) {
                int[] m = new int[plaintext.length()];
                for (int i = 0; i < plaintext.length(); i++) {
                    m[i] = plaintext.charAt(i);
                }
                cipherTextArray = new int[plaintext.length()];
                BigInteger nBig = new BigInteger(n + "");
                String cipherText = "";
                int i;
                for (i = 0; i < m.length; i++) {
                    BigInteger a = new BigInteger(m[i] + "");
                    BigInteger b = a.pow(numOfE);
                    b = b.mod(nBig);
                    cipherTextArray[i] = b.intValue();
                    cipherText = cipherText + cipherTextArray[i];
                }
                encrypted.setText("Encrypted Message: " + cipherText);
                buttonDecrypt.setEnabled(true);
            }
            else {
                BigInteger nBig = new BigInteger(n + "");
                String deCipherText = "";
                for (int i = 0; i < cipherTextArray.length; i++) {
                    BigInteger a = new BigInteger(cipherTextArray[i] + "");
                    BigInteger b = a.pow(d);
                    b = b.mod(nBig);
                    deCipherText = deCipherText + Character.toString((char) b.intValue());
                }
                decrypted.setText("Decrypted Message: " + deCipherText);
            }}}

    public static void main(String[] args) {
        OzelgeRSApp frame = new OzelgeRSApp();
        frame.setTitle("Ozelge's RSA Program!");
        frame.pack();
        frame.getContentPane().setBackground(Color.pink);
        frame.setForeground(Color.pink);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
