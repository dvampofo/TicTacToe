package eecs1022.lab6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Lab6Activity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab6);
    }

    //                                    S P I N N E R

    /*
        First helper method. Changes the content of the output TextView
        Will display what the Transaction is in the GUI.

        Given the id (set in the View xml file) of a TextView
        we change its contents to 'newContents'
    */
    private void setContentsOfTextView(int id, String newContents)
    {
        View view = findViewById(id);
        TextView textView= (TextView) view;
        textView.setText(newContents);
    }

    /*
        This helper method will allow you to select the option in the
        the spinner.
    */
    private String getItemSelected(int id)
    {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }

    //                                  C O N T R O L L E R

    /*
        Helper method for retrieving contents from GUI.

        Given the id(set in the View xml file) of a TextView,
        we change its contents to "newContents".
    */
    private String getInputOfTextField(int id)
    {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }


    public void buttonStartRestart (View view)
    {

        String playerX = getInputOfTextField(R.id.inputPlayerX);
        String playerO = getInputOfTextField(R.id.inputPlayerO);

        String spinnerPlayer = getItemSelected(R.id.spinnerPlayers);
        game = new Game(playerX, playerO, spinnerPlayer);

        setContentsOfTextView(R.id.labelBoard, game.toString());
    }

    public void buttonPlay (View view)
    {
        String row = getItemSelected(R.id.spinnerRow);
        String column = getItemSelected(R.id.spinnerColumn);

        int rowPosition = Integer.parseInt(row) - 1;
        int colPosition = Integer.parseInt(column) - 1;

        game.play(rowPosition, colPosition);

        setContentsOfTextView(R.id.labelBoard, game.toString());
    }
}
