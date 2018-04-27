package asteroids.score;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
        @Override
        public int compare(Score score1, Score score2) {

            double sc1 = score1.getValue();
            double sc2 = score2.getValue();

            if (sc1 > sc2){
                return -1;
            }else if (sc1 < sc2){
                return +1;
            }else{
                return 0;
            }
        }
}