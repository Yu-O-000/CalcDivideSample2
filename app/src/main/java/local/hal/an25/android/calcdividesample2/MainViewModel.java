package local.hal.an25.android.calcdividesample2;

import java.math.BigDecimal;
import java.math.RoundingMode;

import androidx.lifecycle.ViewModel;

/**
 * AN25 Android追加サンプル03 割り算計算機(ViewModel版)
 *
 * メインアクティビティ用ViewModelクラス。
 *
 * @author Shinzo SAITO
 */
public class MainViewModel extends ViewModel {
	/**
	 * 分母フィールド。
	 */
	private double _denomi = 0;
	/**
	 * 分子フィールド。
	 */
	private double _nume = 0;
	/**
	 * 割り算結果の文字列フィールド。
	 */
	private String _ans = "";

	/**
	 * 割り算結果を取得するメソッド。
	 *
	 * @return 割り算結果の文字列。
	 */
	public String getAns() {
		if(_denomi == 0) {
			_ans = "";
		}
		else {
			BigDecimal bigAns = new BigDecimal(_nume / _denomi);
			bigAns = bigAns.setScale(3, RoundingMode.HALF_UP);
				/*
					setScala(①, ②)	... 指定型多数以下の処理を行う。
						① ... 指定桁数
						② ... 処理方法。切り上げ、四捨五入、切り捨てなど
				 */

			_ans = bigAns.toString();
		}
		return _ans;
	}

	/**
	 * 分母の文字列を取得するメソッド。
	 *
	 * @return 分母の文字列。0の場合は空文字。
	 */
	public String getStrDenomi() {
		String strDenomi = "";
		if(_denomi != 0) {
			strDenomi = String.valueOf(_denomi);
		}
		return strDenomi;
	}

	/**
	 * 分子の文字列を取得するメソッド。
	 *
	 * @return 分子の文字列。0の場合は空文字。
	 */
	public String getStrNume() {
		String strNume = "";
		if(_nume != 0) {
			strNume = String.valueOf(_nume);
		}
		return strNume;
	}

	//以下アクセサメソッド。

	public double getDenomi() {
		return _denomi;
	}

	public void setDenomi(double denomi) {
		_denomi = denomi;
	}

	public double getNume() {
		return _nume;
	}

	public void setNume(double nume) {
		_nume = nume;
	}
}

/*
	ViewModel	... アクティビティ用のデータ保持や処理部分を切り分けたクラス。MVCのModelに近い。
					MVVM（Model - View - ViewModel）で使うらしい。
						Model ... DAO呼び出しなど。
						View ... Activity部分
						ViewModel ... データ保持、データアクセス系。
		・フィールド >> UIに必要なデータ用の変数を用意。
		・メソッド >> アクセサメソッド、フィールドを加工するメソッドなどを用意。

		アクティビティサイド
			1. ViewModel用フィールドの用意。
			2. ViewModelの初期化
				※ newではなく、
					ViewModelProvider viewModelProvider = new ViewModelProvider(アクティビティコンテキスト);
					_mainViewModel = viewModelProvider.get(MainViewModel.class);
					となる。
						※ ~~.class ... クラス自体を渡す際に使用。オブジェクトではなく、クラスそのもの。
										内部でインスタンス化などを行ったりしてるらしい。

	画面の回転について
		... 画面を回転するとライフサイクルとしては、finish処理系を通ったあと起動系が通るので、
			入力された未保存データは消えてしまう。
			ViewModelはデータをアクティビティから切り離して保持しているので、ライフサイクルの影響を受けない。
			※ ViewModelはアクティビティと１：１対応推奨。
 */