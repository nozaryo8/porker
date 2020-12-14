package porker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class poker {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String[][] card_mark = {

				{ " -------  ",

						"|A      | ",

						"|       | ",

						"|   *   | ",

						"|       | ",

						"|       | ",

						" -------  " },

				{ " -------  ",

						"|2      | ",

						"|   *   | ",

						"|       | ",

						"|   *   | ",

						"|       | ",

						" -------  " },

				{ " -------  ",

						"|3      | ",

						"|   *   | ",

						"|   *   | ",

						"|   *   | ",

						"|       | ",

						" -------  " },

				{ " -------  ",

						"|4      | ",

						"| *   * | ",

						"|       | ",

						"| *   * | ",

						"|       | ",

						" -------  " },

				{ " -------  ",

						"|5      | ",

						"| *   * | ",

						"|   *   | ",

						"| *   * | ",

						"|       | ",

						" -------  " },

				{ " -------  ",

						"|6      | ",

						"| *   * | ",

						"| *   * | ",

						"| *   * | ",

						"|       | ",

						" -------  " },

				{ " -------  ",

						"|7      | ",

						"| *   * | ",

						"| * * * | ",

						"| *   * | ",

						"|       | ",

						" -------  " },

				{ " -------  ",

						"|8      | ",

						"| * * * | ",

						"| *   * | ",

						"|   *   | ",

						"| *   * | ",

						" -------  " },

				{ " -------  ",

						"|9      | ",
						"| *   * | ",

						"| * * * | ",

						"| *   * | ",

						"| *   * | ",

						" -------  " },

				{ " -------  ",

						"|10     | ",

						"| *   * | ",

						"| * * * | ",

						"| * * * | ",

						"| *   * | ",

						" -------  " },

				{ " -------  ",

						"|J      | ",

						"| ( '-')| ",

						"| /==$=|| ",

						"||=$==/ | ",

						"|(,-,)  | ",

						" -------  " },

				{ " -------  ",

						"|Q      | ",

						"| ( '-')| ",

						"| /==$=|| ",

						"||=$==/ | ",

						"|(,-,)  | ",

						" -------  " },

				{ " -------  ",

						"|K      | ",

						"| ( '-')| ",

						"| /==$=|| ",

						"||=$==/ | ",

						"|(,-,)  | ",

						" -------  " } };

		int[] hand = new int[5];

		int[] hand_number = new int[5];

		String[] hand_suit = new String[5];

		Card Card = new Card();

		card_check card_check = new card_check();

		Card.add_card();

		int[] com_hand = new int[5];//COMの手札の枚数

		int[] com_hand_number = new int[5];//COMの手札の数字

		String[] com_hand_suit = new String[5];//COMの手札のスート

		//コンピューター用

		for (int i = 0; i < com_hand.length; i++) {

			com_hand[i] = Card.take_one_pages();

			com_hand_number[i] = card_check.get_card_number(com_hand[i]);

			com_hand_suit[i] = card_check.get_suit(com_hand[i]);

		}

		//ここから自分用
		for (int i = 0; i < hand.length; i++) {
			hand[i] = Card.take_one_pages();
		}

		//手札の数字、スートを取得

		for (int i = 0; i < hand.length; i++) {

			hand_number[i] = card_check.get_card_number(hand[i]);

			hand_suit[i] = card_check.get_suit(hand[i]);

		}

		//画面表示

		System.out.println("[番号]");
		System.out.println("    1         2         3         4         5      ");

		for (int i = 0; i < hand.length; i++) {
			System.out.print(hand_suit[i]);
		}
		System.out.println();

		int j = 0;
		for (int i = 0; i < card_mark[0].length; i++) {//カードマークを出力(カードマークは７列で構成されている。)
			System.out.println(card_mark[hand_number[j] - 1][i] + card_mark[hand_number[j + 1] - 1][i]
			+ card_mark[hand_number[j + 2] - 1][i] + card_mark[hand_number[j + 3] - 1][i]
			+ card_mark[hand_number[j + 4] - 1][i]);
		}

		System.out.println("交換するカードの番号を入力してください");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//ここに入力

		for (int i = 0; i < hand.length; i++) {
			String str = br.readLine();//readLineはStringを返す。

			if (str.equals("end")) {//endを入力して選択終了
				break;
			}
			int num = Integer.parseInt(str);

			hand[num - 1] = Card.take_one_pages();//選んだカードの番号を山札のカードに入れ替える

		}

		//もう一度ここで手札の数字とスートの取得
		for (int i = 0; i < hand.length; i++) {
			hand_number[i] = card_check.get_card_number(hand[i]);

			hand_suit[i] = card_check.get_suit(hand[i]);

		}

		//コンピューター手札表示
		for (int i = 0; i < 10; i++) {
			System.out.println();
		}

		System.out.println("[コンピューター]");

		for (int i = 0; i < com_hand.length; i++) {
			System.out.print(com_hand_suit[i]);
		}

		System.out.println();

		j = 0;

		for (int i = 0; i < card_mark[0].length; i++) {//COMのカードマーク表示
			System.out.println(card_mark[com_hand_number[j] - 1][i] + card_mark[com_hand_number[j + 1] - 1][i]
					+ card_mark[com_hand_number[j + 2] - 1][i] + card_mark[com_hand_number[j + 3] - 1][i]
					+ card_mark[com_hand_number[j + 4] - 1][i]);
		}

		role_judgment role_judgment = new role_judgment();

		//COMの役判定
		String str = role_judgment.judgment(com_hand_number, com_hand_suit);//役を判定する

		System.out.println();

		System.out.println(str);

		int score_com = score(str);//役を点数に換算する

		for (int i = 0; i < 3; i++) {
			System.out.println();
		}
		//ここからまた自分
		System.out.println("プレイヤー");

		for (int i = 0; i < hand.length; i++) {
			System.out.print(hand_suit[i]);
		}

		System.out.println();

		j = 0;

		for (int i = 0; i < card_mark[0].length; i++) {
			System.out.println(card_mark[hand_number[j] - 1][i] + card_mark[hand_number[j + 1] - 1][i]
					+ card_mark[hand_number[j + 2] - 1][i] + card_mark[hand_number[j + 3] - 1][i]
					+ card_mark[hand_number[j + 4] - 1][i]);
		}

		//自分の役を判定
		str = role_judgment.judgment(hand_number, hand_suit);
		System.out.println();

		System.out.println(str);//役の名前表示

		int score = score(str);//役を点数で評価

		for (int i = 0; i < 3; i++) {
			System.out.println();
		}

		if (score > score_com) {
			System.out.println("あなたの勝ちです");
		} else if (score < score_com) {
			System.out.println("あなたの負けです");
		} else if (score == score_com) {
			System.out.println("引き分けです");
		}
	}

	/**
	 * 取得した役を点数に換算する。
	 * @param str
	 * @return　int 役の点数
	 */
	static int score(String str) {
//あとで定数化
		if (str.equals("ロイヤルストレートフラッシュ")) {
			return 9;

		} else if (str.equals("ストレートフラッシュ")) {
			return 8;

		} else if (str.equals("フォーカード")) {
			return 7;

		} else if (str.equals("フルハウス")) {
			return 6;

		} else if (str.equals("フラッシュ")) {
			return 5;

		} else if (str.equals("ストレート")) {
			return 4;

		} else if (str.equals("スリーカード")) {
			return 3;

		}else if (str.equals("ツーペア")) {
			return 2;

		}else if (str.equals("ワンペア")) {
			return 1;

		}else {
			return 0;
		}
	}

}

class role_judgment {
	//役の名前
	private final String ROYAL_FLUSH = "ロイヤルストレートフラッシュ";
	private final String STRAIGHT_FLUSH = "ストレートフラッシュ";
	private final String STRAIGHT = "ストレート";
	private final String FOUR_OF_A_KIND = "フォーカード";
	private final String A_FULL_HOUSE = "フルハウス";
	private final String THREE_OF_A_KIND = "スリーカード";
	private final String FLUSH = "フラッシュ";
	private final String TWO_PAIR = "ツーペア";
	private final String A_PAIR = "ワンペア";
	private final String NO_PAIR = "ノーペア";
/**
 * 役を判定する
 * @param hand_number
 * @param hand_suit
 * @return String 役の判定
 */
	String judgment(int[] hand_number, String[] hand_suit) {

		int[] X = aggregate(hand_number);

		if (royal_straight_flush(hand_number, hand_suit)) {
			return ROYAL_FLUSH;
		}
		if (straight(hand_number)) {
			return flush(hand_suit) ? STRAIGHT_FLUSH : STRAIGHT;
		}
		if (X[4] == 1) {
			return FOUR_OF_A_KIND;
		}
		if (X[3] == 1) {
			return X[2] == 1 ? A_FULL_HOUSE : THREE_OF_A_KIND;
		}
		if (flush(hand_suit)) {
			return FLUSH;
		}
		if (X[2] == 2) {
			return TWO_PAIR;
		}
		if (X[2] == 1) {
			return A_PAIR;
		}
		return NO_PAIR;

	}

	/**
	 * 数字がペアになっているカードを集計する。詳細はEXCEL
	 * @param hand_number
	 * @return int[]
	 */
	private int[] aggregate(int hand_number[]) {

		int[] count = new int[14];

		int[] aggregate = new int[5];

		//持っているカードの数字の箱をインクリメントする
		//1を持っていたら1の箱をインクリメント
		for (int i = 0; i < 5; i++) {
			++count[hand_number[i]];
		}

		//ペアになっている枚数分の箱をインクリメント
		//持ってない数字は０をインクリメント１枚持っていた場合1の箱をインクリメント
		for (int m: count) {
			++aggregate[m];
		}
		return aggregate;

	}

	/**
	 * 数字を小さい順に入れ替える。
	 * @param hand_number
	 * @return int[]
	 */
	private int[] sort(int hand_number[]) {

		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < 4 - i; j++) {

				if (hand_number[j] > hand_number[j + 1]) {

					int temp = hand_number[j];

					hand_number[j] = hand_number[j + 1];

					hand_number[j + 1] = temp;

				}
			}
		}
		return hand_number;

	}

	/**
	 * ストレート:A,10,11,12,13の時の役
	 * @param hand_number
	 * @return boolean
	 */
	private boolean straight(int hand_number[]) {

		hand_number = sort(hand_number);

		return (hand_number[0] == 1 &&

				hand_number[1] == 10 &&

				hand_number[2] == 11 &&

				hand_number[3] == 12 &&

				hand_number[4] == 13) ||

				(hand_number[1] == hand_number[0] + 1) &&

				(hand_number[2] == hand_number[0] + 2) &&

				(hand_number[3] == hand_number[0] + 3) &&

				(hand_number[4] == hand_number[0] + 4);

	}

	/**
	 * フラッシュ:５枚同じスートの時の役
	 * 配列の０番目と他のカードのスートを比較している
	 * @param hand_suit
	 * @return boolean
	 */
	private boolean flush(String hand_suit[]) {

		return (hand_suit[0] == hand_suit[1]) &&

				(hand_suit[0] == hand_suit[2]) &&

				(hand_suit[0] == hand_suit[3]) &&

				(hand_suit[0] == hand_suit[4]);

	}

	/**
	 * ロイヤルストレートフラッシュ:同じスートかつA,10,11,12,13の連番
	 * @param hand_number
	 * @param hand_suit
	 * @return boolean
	 */
	private boolean royal_straight_flush(int hand_number[], String hand_suit[]) {

		hand_number = sort(hand_number);

		return (flush(hand_suit) == true) &&

				(straight(hand_number) == true) &&

				(hand_number[0] == 1) &&

				(hand_number[1] == 10) &&

				(hand_number[2] == 11) &&

				(hand_number[3] == 12) &&

				(hand_number[4] == 13);

	}

}


class Card {

	ArrayList<Integer> all_card = new ArrayList<Integer>();

	/**
	 * 山札にカードを52枚追加しシャッフルする
	 */
	void add_card() {//カードの中身は持っていない。52個の数字を用意しているだけ

		for (int i = 0; i < 52; ++i) {
			all_card.add(i);
		}
		Collections.shuffle(all_card);

	}

	/**
	 * シャッフルする
	 */
	public void shuffle() {
		Collections.shuffle(all_card);
	}

	/**
	 * カードを手札に加え山札から削除する。
	 * @return ID
	 */
	public int take_one_pages() {
		return all_card.remove(0);
	}

}

class card_check {

	private int ID;

	private final String suit[] = { " ハート    ", "スペード   ", " ダイヤ    ", "クローバー  " };

	private final String card_number[] = { "0", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	/**
	 * 数字のチェック
	 * (Excelに詳細あり)
	 * @param ID
	 * @return カードの数字
	 */
	public int get_card_number(int ID) {
		return ID % 13 + 1;
	}

	/**
	 * スートのチェック
	 * (Excelに詳細あり)
	 * @param ID
	 * @return カードのスート
	 */
	public String get_suit(int ID) {
		return suit[ID / 13];
	}

}