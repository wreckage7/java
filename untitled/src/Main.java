package ru.fontany.remcontrol;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import java.net.*;
import java.io.*;
import java.util.Arrays;


public class MainActivity extends Activity {

    TextView mState;
    ImageButton imB[];
    ImageButton imCompos[];
    SeekBar sVolume;
    SeekBar sHydro;
    String line;
    Socket sPlayer;
    InputStream inData;
    OutputStream outData;
    DataInputStream inD;
    DataOutputStream outD = new DataOutputStream(outData);
    String address = "192.168.1.180";
    int dstPort = 33333;
    byte one_1 = 1;



    private void getConnect(String addr, int Port) throws IOException {

        InetAddress ipAddress = InetAddress.getByName(addr);
        sPlayer = new Socket(ipAddress,Port);
        inData = sPlayer.getInputStream();
        outData = sPlayer.getOutputStream();
        inD = new DataInputStream(inData);
        outD = new DataOutputStream((outData));

    }

    private void sendCmd(String cmd) throws IOException
    {
        byte[] buf;
        int k;

        String getPL = cmd;
        buf = new byte[getPL.length()+8+1];
        for (int i=0;i< buf.length;i++) buf[i] = 0;
        //  8 байт длина unsigned long  в С++, как в Пашиной программе
        k=0;
        byte[] tmp = getPL.getBytes();
        for (int j=8;j<getPL.length()+8;j++) buf[j]=tmp[k++];
        buf[0] = (byte)((byte)getPL.length() + 1);//добавил +1, для расчета корректной длины строки
        //outD.writeUTF(getPL);
        outD.write(buf, 0, buf.length);
        outD.flush();

    }

    private void trdata(int iCmd) throws IOException {
        byte[] buf;
        int k;


        if (iCmd == 1){

            //String getPL = "Storage::get_playlists";
            String getPL = "Player::PlayCurrent";
            buf = new byte[getPL.length()+8+1];
            for (int i=0;i< buf.length;i++) buf[i] = 0;
            // 8 байт длина unsigned long  в С++, как в Пашиной программе
            k=0;
            byte[] tmp = getPL.getBytes();
            for (int j=8;j<getPL.length()+8;j++) buf[j]=tmp[k++];
            byte bLen= (byte)getPL.length();
            buf[0] = (byte) (bLen+one_1);
            //outD.writeUTF(getPL);
            outD.write(buf, 0, buf.length);
            outD.flush();

            //line = inD.readUTF();
            buf = new byte[65535];
            for (int i=0;i< buf.length;i++) buf[i] = 0;
            int rc = inD.read(buf);
            line = new String(buf); //Arrays.toString(buf);

            mState.setText(getPL);

        }

        if (iCmd == 2){

            //String getPL = "Storage::get_playlists";
            String getPL = "Player::StopPlay";
            buf = new byte[getPL.length()+8+1];
            for (int i=0;i< buf.length;i++) buf[i] = 0;
            // 8 байт длина unsigned long  в С++, как в Пашиной программе
            k=0;
            byte[] tmp = getPL.getBytes();
            for (int j=8;j<getPL.length()+8;j++) buf[j]=tmp[k++];
            byte bLen= (byte)getPL.length();
            buf[0] = (byte) (bLen+one_1);
            //outD.writeUTF(getPL);
            outD.write(buf,0,buf.length);
            outD.flush();

            //line = inD.readUTF();
            buf = new byte[65535];
            for (int i=0;i< buf.length;i++) buf[i] = 0;
            int rc = inD.read(buf);
            line = new String(buf); //Arrays.toString(buf);
            //mState.setText(line);
            mState.setText(getPL);

        }


        if (iCmd == 3){

            //След композиция
            String getPL = "Player::NextComposition";
            buf = new byte[getPL.length()+8+1];
            for (int i=0;i< buf.length;i++) buf[i] = 0;
            // 8 байт длина unsigned long  в С++, как в Пашиной программе
            k=0;
            byte[] tmp = getPL.getBytes();
            for (int j=8;j<getPL.length()+8;j++) buf[j]=tmp[k++];
            byte bLen= (byte)getPL.length();
            buf[0] = (byte) (bLen+one_1);
            //outD.writeUTF(getPL);
            outD.write(buf,0,buf.length);
            outD.flush();

            //line = inD.readUTF();
            for (int i=0;i< buf.length;i++) buf[i] = 0;
            int rc = inD.read(buf);
            line = Arrays.toString(buf);
            //mState.setText(line);
            mState.setText(getPL);

        }
        if (iCmd == 4){

            //предыдущая
            String getPL = "Player::PreviousComposition";
            buf = new byte[getPL.length()+8+1];
            for (int i=0;i< buf.length;i++) buf[i] = 0;
            // 8 байт длина unsigned long  в С++, как в Пашиной программе
            k=0;
            byte[] tmp = getPL.getBytes();
            for (int j=8;j<getPL.length()+8;j++) buf[j]=tmp[k++];
            byte bLen= (byte)getPL.length();
            buf[0] = (byte) (bLen+one_1);
            //outD.writeUTF(getPL);
            outD.write(buf,0,buf.length);
            outD.flush();

            //line = inD.readUTF();
            for (int i=0;i< buf.length;i++) buf[i] = 0;
            int rc = inD.read(buf);
            line = Arrays.toString(buf);
            //mState.setText(line);
            mState.setText(getPL);

        }
        if (iCmd == 5){

            //
            //String getPL = "Player::PlayComposition Run_Like_Hell";
            String getPL = "Player::PlayComposition La-Mer";
            buf = new byte[getPL.length()+8+1];
            for (int i=0;i< buf.length;i++) buf[i] = 0;
            // 8 байт длина unsigned long  в С++, как в Пашиной программе
            k=0;
            byte[] tmp = getPL.getBytes();
            for (int j=8;j<getPL.length()+8;j++) buf[j]=tmp[k++];
            byte bLen= (byte)getPL.length();
            buf[0] = (byte) (bLen+one_1);
            //outD.writeUTF(getPL);
            outD.write(buf,0,buf.length);
            outD.flush();

            //line = inD.readUTF();
            for (int i=0;i< buf.length;i++) buf[i] = 0;
            int rc = inD.read(buf);
            line = Arrays.toString(buf);
            //mState.setText(line);
            mState.setText(getPL);

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            getConnect(address,dstPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //обрабатываем события от звукового ползунка
        sVolume = (SeekBar) findViewById(R.id.seekBar);
        sVolume.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                                               int prgChanged = 0;

                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                   prgChanged = progress;
                                               }

                                               @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {

                                               }

                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {
                                                   String sCmd = String.format("Player::SetVolume %d",prgChanged);
                                                   try {
                                                       sendCmd(sCmd);
                                                   } catch (IOException e){
                                                       e.printStackTrace();
                                                   }

                                               }
                                           }

        );

        sHydro = (SeekBar) findViewById(R.id.seekBar2);
        sHydro.setOnSeekBarChangeListener( new OnSeekBarChangeListener() {
                                               int prgHydro = 0;
                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                   prgHydro = progress;
                                               }

                                               @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {

                                               }

                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {
                                                   String sCmd = String.format("Player::SetHydroPercent %d",prgHydro);
                                                   try {
                                                       sendCmd(sCmd);
                                                   } catch (IOException e){
                                                       e.printStackTrace();
                                                   }

                                               }
                                           }

        );

        imB = new ImageButton[4];

        mState = (TextView) findViewById(R.id.textView);

        imB[0] = (ImageButton) findViewById(R.id.imageButton);

        imB[0].setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //mState.setText("PLAY PRESSED");
                        try {
                            trdata(1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        imB[1] = (ImageButton) findViewById(R.id.imageButton2);

        imB[1].setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            trdata(2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        imB[2] = (ImageButton) findViewById(R.id.imageButton3);

        imB[2].setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            trdata(4);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );

        imB[3] = (ImageButton) findViewById(R.id.imageButton4);

        imB[3].setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            trdata(3);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

// здесь мы будем вызывать готовые композиции
//-------------------------------------------

        imCompos = new ImageButton[6];

        imCompos[0] = (ImageButton) findViewById(R.id.imageButton5);

        imCompos[0].setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            trdata(5);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        imCompos[1] = (ImageButton) findViewById(R.id.imageButton6);

        imCompos[1].setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            trdata(6);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        imCompos[2] = (ImageButton) findViewById(R.id.imageButton7);

        imCompos[2].setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            trdata(7);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        imCompos[3] = (ImageButton) findViewById(R.id.imageButton8);

        imCompos[3].setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            trdata(8);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        imCompos[4] = (ImageButton) findViewById(R.id.imageButton9);

        imCompos[4].setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            trdata(9);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        imCompos[5] = (ImageButton) findViewById(R.id.imageButton10);

        imCompos[5].setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            trdata(10);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
