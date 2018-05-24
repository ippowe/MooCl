package kr.co.moocl.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ChkService {

	static int global_index;

	public void chk(List<String[]> wordList, int idx, List<String[]> conList, List<List<String[]>> finalList) {
		if (idx + 1 == wordList.size()) {
			System.out.println("끝지점 도달");
			global_index = idx;
			return;
		} else {
			if (wordList.get(idx)[1].equals("NNG") || wordList.get(idx)[1].equals("NNP")) {
				if (idx + 1 < wordList.size()
						&& (wordList.get(idx + 1)[1].equals("NNG") || wordList.get(idx + 1)[1].equals("NNP"))) {
					conList.add(wordList.get(idx + 1));
					finalList.add(conList.stream().collect(Collectors.toList()));

					chk(wordList, idx + 1, conList, finalList);
				} else if (idx + 1 < wordList.size() && wordList.get(idx + 1)[1].equals("JKG")) {

					if (idx + 2 < wordList.size()
							&& (wordList.get(idx + 2)[1].equals("NNG") || wordList.get(idx + 2)[1].equals("NNP"))) {
						conList.add(wordList.get(idx + 1));
						conList.add(wordList.get(idx + 2));
						finalList.add(conList.stream().collect(Collectors.toList()));

						chk(wordList, idx + 2, conList, finalList);
					}

				}

			} else if (wordList.get(idx)[1].equals("VA")) {
				if (idx + 1 < wordList.size()
						&& (wordList.get(idx + 1)[1].equals("NNG") || wordList.get(idx + 1)[1].equals("NNP"))) {
					conList.add(wordList.get(idx + 1));
					finalList.add(conList.stream().collect(Collectors.toList()));
					chk(wordList, idx + 1, conList, finalList);

					if (idx + 2 < wordList.size() && wordList.get(idx + 2)[1].equals("MAG")) {
						conList.add(wordList.get(idx + 2));
						finalList.add(conList.stream().collect(Collectors.toList()));
						chk(wordList, idx + 2, conList, finalList);
					}
				} else if (idx + 1 < wordList.size()
						&& (wordList.get(idx + 1)[1].equals("EC") || wordList.get(idx + 1)[1].equals("ET"))) {
					conList.add(wordList.get(idx + 1));
					finalList.add(conList.stream().collect(Collectors.toList()));
					chk(wordList, idx + 1, conList, finalList);
				} else if (idx + 2 < wordList.size() && wordList.get(idx + 1)[1].equals("ETM")
						&& (wordList.get(idx + 2)[1].equals("NNG") || wordList.get(idx + 2)[1].equals("NNP"))) {
					conList.add(wordList.get(idx + 1));
					conList.add(wordList.get(idx + 2));
					finalList.add(conList.stream().collect(Collectors.toList()));
					chk(wordList, idx + 2, conList, finalList);
				}

			} else if (wordList.get(idx)[1].equals("MAG")) {
				if (idx + 1 < wordList.size()
						&& (wordList.get(idx + 1)[1].equals("VA") || wordList.get(idx + 1)[1].equals("XR"))) {
					conList.add(wordList.get(idx + 1));
					finalList.add(conList.stream().collect(Collectors.toList()));
					chk(wordList, idx + 1, conList, finalList);
				}
			} else if (wordList.get(idx)[1].equals("XR")) {
				if (idx + 3 < wordList.size() && wordList.get(idx + 1)[1].equals("XSA")
						&& wordList.get(idx + 2)[1].equals("ETM")
						&& (wordList.get(idx + 3)[1].equals("NNG") || wordList.get(idx + 3)[1].equals("NNP"))) {
					conList.add(wordList.get(idx + 1));
					conList.add(wordList.get(idx + 2));
					conList.add(wordList.get(idx + 3));
					finalList.add(conList.stream().collect(Collectors.toList()));
					chk(wordList, idx + 3, conList, finalList);
				}
			}
		}
	}
	public void pickup(List<String[]> wordList, List<List<String[]>> finalList) {
		for (String[] word : wordList) {
			if (word[1].equals("NNG")||
				word[1].equals("NNP")||
				word[1].equals("NNB")||
				word[1].equals("VA")||
				word[1].equals("NA")||
				word[1].equals("NF")||
				word[1].equals("NV")) {
				List<String[]> tempList = new LinkedList<>();
				tempList.add(word);
				finalList.add(tempList);
			}
		}
	}

}
