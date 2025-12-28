import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Queue<Character> q = new Queue<>();
        q.insert('a');
        q.insert('a');
        q.insert('c');
        q.insert('b');

        Queue<Integer> q2 = new Queue<>();
        q2.insert(2);
        q2.insert(5);
        q2.insert(4);

        Queue<String> q3 = new Queue<>();
        q3.insert("bye");
        q3.insert("hi");
        q3.insert("bye");

        System.out.println(ex1(q));
        System.out.println(ex2(q3));
        System.out.println(ex3(q2));
        System.out.println(ex4(q2));
    }


    public static Queue<Integer> copyQueue(Queue<Integer> q) {
        Queue<Integer> copy = new Queue<>();
        Queue<Integer> temp = new Queue<>();

        while (!q.isEmpty()) {
            int x = q.remove();
            copy.insert(x);
            temp.insert(x);
        }

        while (!temp.isEmpty()) {
            q.insert(temp.remove());
        }

        return copy;
    }

    public static boolean isIn(Queue<String> q, String x) {
        Queue<String> q1 = new Queue<String>();
        boolean found = false;

        while (!q.isEmpty()) {
            String temp = q.remove();
            if (temp.equals(x)) {
                found = true;
            }
            q1.insert(temp);
        }

        while (!q1.isEmpty()) {
            q.insert(q1.remove());
        }

        return found;
    }

    // ex1 
    // יעילות זמן: O(n)
    // כל איבר נשלף פעם אחת בלבד (גם הלולאה הפנימית לא מוסיפה מעבר נוסף)
    // יעילות זיכרון: O(n) – תור חדש בגודל מקסימלי n
    public static Queue<Integer> ex1(Queue<Character> q) {
        Queue<Integer> q1 = new Queue<Integer>();

        while (!q.isEmpty()) {
            char x = q.remove();
            int count = 1;

            while (!q.isEmpty() && x == q.head()) {
                count++;
                q.remove();
            }

            q1.insert(count);
        }

        return q1;
    }

    //ex2 
    // יעילות זמן: O(n^2)
    // לולאה חיצונית שרצה n פעמים ובכל פעם קריאה ל-isIn שסורקת את התור
    // יעילות זיכרון: O(n) – שימוש בתור עזר לשחזור התור המקורי
    public static boolean ex2(Queue<String> q) {
        Queue<String> q1 = new Queue<String>();
        boolean ans = false;

        while (!q.isEmpty()) {
            String x = q.remove();

            if (isIn(q, x)) {
                ans = true;
            }

            q1.insert(x);
        }

        while (!q1.isEmpty()) {
            q.insert(q1.remove());
        }

        return ans;
    }

    //ex3
    // יעילות זמן: O(n^2)
    // לכל איבר מתבצעת סריקה על שאר האיברים כדי להסיר כפילויות
    // יעילות זיכרון: O(n) – שימוש במספר תורי עזר
    public static Queue<Integer> ex3(Queue<Integer> q) {
        Queue<Integer> q0 = copyQueue(q);
        Queue<Integer> q1 = new Queue<Integer>();
        Queue<Integer> q2 = new Queue<Integer>();

        while (!q0.isEmpty()) {
            int x = q0.remove();

            while (!q0.isEmpty()) {
                if (x == q0.head()) {
                    q0.remove();
                } else {
                    q1.insert(q0.remove());
                }
            }

            q2.insert(x);

            while (!q1.isEmpty()) {
                q0.insert(q1.remove());
            }
        }

        return q2;
    }

    //ex4
    // יעילות זמן: O(n^2)
    // בכל איטרציה נמצא מינימום ע"י מעבר על כל התור
    // יעילות זיכרון: O(n) – שימוש בתורי עזר
    public static Queue<Integer> ex4(Queue<Integer> q) {
        Queue<Integer> q1 = new Queue<Integer>();
        Queue<Integer> q2 = new Queue<Integer>();

        while (!q.isEmpty()) {
            int min = q.remove();

            while (!q.isEmpty()) {
                if (q.head() < min) {
                    q1.insert(min);
                    min = q.remove();
                } else {
                    q1.insert(q.remove());
                }
            }

            q2.insert(min);

            while (!q1.isEmpty()) {
                q.insert(q1.remove());
            }
        }

        return q2;
    }
}
