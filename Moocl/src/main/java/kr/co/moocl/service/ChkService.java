package kr.co.moocl.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ChkService {

	static int global_index;

	public void chk(List<String[]> wordList, int idx, List<String[]> conList, List<String> finalList) {
		if (idx + 1 == wordList.size()) {
//			System.out.println("끝지점 도달");
			global_index = idx;
			return;
		} else {
			if (wordList.get(idx)[1].equals("NNG") || wordList.get(idx)[1].equals("NNP")|| wordList.get(idx)[1].equals("NNB") ) {
				if (idx + 1 < wordList.size()
						&& (wordList.get(idx + 1)[1].equals("NNG") || wordList.get(idx + 1)[1].equals("NNP")|| wordList.get(idx+1)[1].equals("NNB") )) {
					conList.add(wordList.get(idx + 1));
//					finalList.add(conList.stream().collect(Collectors.toList()));
					finalList.add(wordMarkingFunction(conList.stream().collect(Collectors.toList())));

					chk(wordList, idx + 1, conList, finalList);
				} else if (idx + 1 < wordList.size() && wordList.get(idx + 1)[1].equals("JKG")) {

					if (idx + 2 < wordList.size()
							&& (wordList.get(idx + 2)[1].equals("NNG") || wordList.get(idx + 2)[1].equals("NNP")|| wordList.get(idx+2)[1].equals("NNB") )) {
						conList.add(wordList.get(idx + 1));
						conList.add(wordList.get(idx + 2));
//						finalList.add(conList.stream().collect(Collectors.toList()));
						finalList.add(wordMarkingFunction(conList.stream().collect(Collectors.toList())));

						chk(wordList, idx + 2, conList, finalList);
					}

				}else if (idx + 1 < wordList.size()
						&& (wordList.get(idx + 1)[1].equals("VA"))) {
					conList.add(wordList.get(idx + 1));
					finalList.add(wordMarkingFunction(conList.stream().collect(Collectors.toList())));
				}

			} else if (wordList.get(idx)[1].equals("VA")) {
				if (idx + 1 < wordList.size()
						&& (wordList.get(idx + 1)[1].equals("NNG") || wordList.get(idx + 1)[1].equals("NNP")|| wordList.get(idx+1)[1].equals("NNB") )) {
					conList.add(wordList.get(idx + 1));
					finalList.add(wordMarkingFunction(conList.stream().collect(Collectors.toList())));
					chk(wordList, idx + 1, conList, finalList);

					if (idx + 2 < wordList.size() && wordList.get(idx + 2)[1].equals("MAG")) {
						conList.add(wordList.get(idx + 2));
						finalList.add(wordMarkingFunction(conList.stream().collect(Collectors.toList())));
						chk(wordList, idx + 2, conList, finalList);
					}
				} 
//				else if (idx + 1 < wordList.size()
//						&& (wordList.get(idx + 1)[1].equals("EC"))) {
//					conList.add(wordList.get(idx + 1));
//					finalList.add(wordMarkingFunction(conList.stream().collect(Collectors.toList())));
//					chk(wordList, idx + 1, conList, finalList);
//				} 
				else if (idx + 2 < wordList.size() && wordList.get(idx + 1)[1].equals("ETM")
						&& (wordList.get(idx + 2)[1].equals("NNG") || wordList.get(idx + 2)[1].equals("NNP")|| wordList.get(idx+2)[1].equals("NNB") )) {
					conList.add(wordList.get(idx + 1));
					conList.add(wordList.get(idx + 2));
					finalList.add(wordMarkingFunction(conList.stream().collect(Collectors.toList())));
					chk(wordList, idx + 2, conList, finalList);
				}

			} else if (wordList.get(idx)[1].equals("MAG")) {
				if (idx + 1 < wordList.size()
						&& (wordList.get(idx + 1)[1].equals("VA") || wordList.get(idx + 1)[1].equals("XR"))) {
					conList.add(wordList.get(idx + 1));
					finalList.add(wordMarkingFunction(conList.stream().collect(Collectors.toList())));
					chk(wordList, idx + 1, conList, finalList);
				}
			} else if (wordList.get(idx)[1].equals("XR")) {
				if (idx + 3 < wordList.size() && wordList.get(idx + 1)[1].equals("XSA")
						&& wordList.get(idx + 2)[1].equals("ETM")
						&& (wordList.get(idx + 3)[1].equals("NNG") || wordList.get(idx + 3)[1].equals("NNP")|| wordList.get(idx+3)[1].equals("NNB") )) {
					conList.add(wordList.get(idx + 1));
					conList.add(wordList.get(idx + 2));
					conList.add(wordList.get(idx + 3));
					finalList.add(wordMarkingFunction(conList.stream().collect(Collectors.toList())));
					chk(wordList, idx + 3, conList, finalList);
				}
			}
		}
	}

	public void pickup(List<String[]> wordList, List<String> finalList) {
		for (String[] word : wordList) {
			if (word[1].equals("NNG") || word[1].equals("NNP") 
					|| word[1].equals("NA") || word[1].equals("NF") || word[1].equals("NV")|| word[1].equals("IC")) {
				finalList.add(word[0]);
			}else if(word[1].equals("VA")) {
				finalList.add(vaChange(word)[0]);
			}
			
		}
	}

	public List<Character> fml(String word) {
		int BASE_CODE = 44032;
		int CHOSUNG = 588;
		int JUNGSUNG = 28;
		final char[] CHOSUNG_LIST = { 'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ',
				'ㅌ', 'ㅍ', 'ㅎ' };
		final char[] JUNGSUNG_LIST = { 'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ',
				'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ' };
		final char[] JONGSUNG_LIST = { ' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ',
				'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ' };

		List<Character> result = new LinkedList<>();

		for (int i = 0; i < word.length(); i++) {
			int ac = word.charAt(i) - BASE_CODE;
			int cho = (word.charAt(i) - BASE_CODE) / CHOSUNG;
			int jung = ((word.charAt(i) - BASE_CODE) - (CHOSUNG * cho)) / JUNGSUNG;
			int jong = ((word.charAt(i) - BASE_CODE) - (CHOSUNG * cho) - (JUNGSUNG * jung));
//			System.out.println("'"+CHOSUNG_LIST[cho]+"'");
//			System.out.println("'"+JUNGSUNG_LIST[jung]+"'");
//			System.out.println("'"+JONGSUNG_LIST[jong]+"'");
			
			result.add(CHOSUNG_LIST[cho]);
			result.add(JUNGSUNG_LIST[jung]);
			if(jong == 0) {
				result.add(' ');
			}else {
				result.add(JONGSUNG_LIST[jong]);
			}
		}
		return result;
	}
	
	
	public char makeChar(char chosung, char jungsung, char jongsung) {
		int BASE_CODE = 44032;
		int CHOSUNG = 588;
		int JUNGSUNG = 28;
		char[] CHOSUNG_LIST = {'ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
		char[] JUNGSUNG_LIST = { 'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'};
		char[] JONGSUNG_LIST = { ' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 
				'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ' };
		int cho = 0 ;
		int jung = 0;
		int jong = 0;
		
		for (int i = 0 ; i < CHOSUNG_LIST.length ; i ++) {
			if((int)chosung == (int)CHOSUNG_LIST[i]) {
				cho = i;
				break;
			}
		}
		
		for (int i = 0 ; i < JUNGSUNG_LIST.length ; i ++) {
			if(jungsung== JUNGSUNG_LIST[i]) {
				jung = i;
				break;
			}
		}
		
		for (int i = 0 ; i < JONGSUNG_LIST.length ; i ++) {
			if(jongsung== JONGSUNG_LIST[i]) {
				jong = i;
				break;
			}
		}
		
		char result = (char) (jong+(JUNGSUNG * jung)+(CHOSUNG * cho)+BASE_CODE);
		return result;

	}
	
	public String[] vaChange(String[] va) {
		List<Character> splitWord = fml(va[0]);
		//true : 음
		//false : 움
		boolean chk = true;
		if(splitWord.get(splitWord.size()-1)==' ') {
			splitWord.set(splitWord.size()-1, 'ㅁ');
			
		}else if(splitWord.get(splitWord.size()-1)=='ㅂ') {
			splitWord.set(splitWord.size()-1, ' ');
			chk= false;
		}
		String temp = "";
		for(int j = 0 ; j < splitWord.size()/3; j ++) {
			temp += makeChar(splitWord.get(j*3),splitWord.get(j*3+1),splitWord.get(j*3+2));
		}
		if(chk) {
			temp+="음";
		}else {
			temp+="움";
		}
		va[0] = temp;
		return va;
	}

	public String wordMarkingFunction(List<String[]> wordList) {
		String finalWord = "";
		List<String> result = new LinkedList<>();
		int i = 0;
		while (i < wordList.size()) {
			if (wordList.get(i)[1].equals("ETM") && wordList.get(i)[0].equals("ㄴ")) {
				List<Character> splitWord = fml(wordList.get(i - 1)[0]);
				if(splitWord.get(splitWord.size()-1)==' ') {
					splitWord.set(splitWord.size()-1, 'ㄴ');
					wordList.remove(i);
				} else if(splitWord.get(splitWord.size()-1) =='ㅂ') {
					splitWord.set(splitWord.size()-1, ' ');
					String[] o = {"운","GURUMI"};
					wordList.set(i,o);
				}
				String temp = "";
				for(int j = 0 ; j < splitWord.size()/3; j ++) {
					temp += makeChar(splitWord.get(j*3),splitWord.get(j*3+1),splitWord.get(j*3+2));
				}
				String[] gurumiWord = {temp,"GURUMI"};
				wordList.set(i-1,gurumiWord);
			} 
			
			if(i == wordList.size()-1 && wordList.get(i)[1].equals("VA")) {
				System.out.println(wordList.get(i)[0]);
				System.out.println(wordList.get(i)[1]);
				wordList.set(i,vaChange(wordList.get(i).clone()));
			}
			
			i++;
		}
		
		for(String [] word : wordList) {
			finalWord += word[0];
		}
		return finalWord;
	}
}