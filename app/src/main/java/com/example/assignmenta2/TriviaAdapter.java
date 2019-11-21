package com.example.assignmenta2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TriviaAdapter extends RecyclerView.Adapter<TriviaAdapter.TriviaViewHolder> {

    public static List<Trivia> triviaToAdapt;;

    public void setData(List<Trivia> triviaToAdapt) {
        this.triviaToAdapt = triviaToAdapt;
    }

    @NonNull
    @Override
    public TriviaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.trivia, parent, false);

        TriviaViewHolder triviaViewHolder = new TriviaViewHolder(view);
        return triviaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TriviaViewHolder holder, int position) {
        final Trivia triviaAtPosition = triviaToAdapt.get(position);
        final Context context = holder.view.getContext();
        holder.bind(triviaAtPosition);
    }

    @Override
    public int getItemCount() {
        return triviaToAdapt.size();
    }



    public static class TriviaViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView questionText;
        public TextView answert;
        public Button answerb;
        public Button wrongt;
        public TextView wrongp;
        public TextView value;

        public TriviaViewHolder(View v){
            super(v);
            view = v;
            questionText = v.findViewById(R.id.questionText);
            answert = v.findViewById(R.id.answertext);
            answerb = v.findViewById(R.id.answerbutton);
            wrongt = v.findViewById(R.id.wrong);
            wrongp = v.findViewById(R.id.wrongprompt);
            value = v.findViewById(R.id.valuetext);
        }
        public void bind(final Trivia trivia) {

            questionText.setText(trivia.getQuestion());
            value.setText("Value: " + Integer.toString(trivia.getValue()));

            answerb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s1 = trivia.getAnswer();
                    String replaceString = s1.replace("<i>","");
                    String s2 = replaceString;
                    final String replaceString3 = s2.replace("</i>", "");
                    answert.setText(replaceString3);
                    answert.setTextColor(Color.GREEN);

                    Intent intent1 = new Intent(v.getContext(), TriviaRecycler.class);
                    intent1.putExtra("score", trivia.getValue());
                    intent1.putExtra("yes", 1);
                }
            });

            wrongt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s1 = trivia.getAnswer();
                    String replaceString = s1.replace("<i>","");
                    String s2 = replaceString;
                    final String replaceString3 = s2.replace("</i>", "");
                    answert.setText(replaceString3);
                    answert.setTextColor(Color.RED);
                    wrongp.setText("Still don't understand the right answer? Click here to find out more!");
                    wrongp.setVisibility(View.VISIBLE);
                    wrongp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + trivia.getQuestion()));
                            v.getContext() .startActivity(implicit);
                        }
                    });
                }
            });
        }
    }
}
