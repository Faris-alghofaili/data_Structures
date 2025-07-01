import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ADTWordAnalysis {

	LinkedList<WordInformation>[] arrayOfDifferentLengths;
	WordInformation[] sortedArray;
	int n;
	int m;

	ADTWordAnalysis(int k, File input) throws FileNotFoundException {
		arrayOfDifferentLengths = new LinkedList[k];
		n = m = 0;
		int i = 0;
		while (i < k) {
			arrayOfDifferentLengths[i] = new LinkedList();
			i++;
		}
		Scanner scan = new Scanner(input);
		int row = 1;
		while (scan.hasNext())
		{
			String[] str = scan.nextLine().split("\\s+");
			i = 0;
			while (i < str.length)
			{
				insert(removePunc(str[i]), row, i + 1);
				i++;
			}
			row++;
		}
		SortedArray();
	}
	private String removePunc(String word) {
		while ((String.valueOf(word.charAt(0)).matches("\\W"))) {
			word = word.substring(1);
		}
		while ((String.valueOf(word.charAt(word.length() - 1)).matches("\\W"))) {
			word = word.substring(0, word.length() - 1);
		}
		return word;
	}

	private void SortedArray() {
		sortedArray = new WordInformation[m];
		int index = 0;
		for (LinkedList<WordInformation> list : arrayOfDifferentLengths) {
			Node<WordInformation> temp = list.head;
			while (temp != null) {
				sortedArray[index] = temp.data;
				index++;
				temp = temp.next;
			}
		}
		mergeSort(sortedArray, m);
	}

	private void mergeSort(WordInformation[] a, int n) {
		if (n < 2) {
			return;
		}
		int mid = n / 2, i = 0;
		WordInformation[] l = new WordInformation[mid];
		while (i < mid) {
			l[i] = a[i];
			i++;
		}
		i = mid;
		WordInformation[] r = new WordInformation[n - mid];
		while (i < n) {
			r[i - mid] = a[i];
			i++;
		}
		mergeSort(l, mid);
		mergeSort(r, n - mid);
		merge(a, l, r, mid, n - mid);
	}

	private void merge(WordInformation[] a, WordInformation[] l, WordInformation[] r, int left, int right) {
		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
			if (l[i].size >= r[j].size) {
				a[k] = l[i];
				k++;
				i++;
			} else {
				a[k] = r[j];
				k++;
				j++;
			}
		}
		while (i < left) {
			a[k] = l[i];
			i++;
			k++;
		}
		while (j < right) {
			a[k] = r[j];
			k++;
			j++;
		}
	}

	private void insert(String word, int line, int pos) {
		int length = word.length() - 1;
		WordInformation e = new WordInformation(word, line, pos);
		if (arrayOfDifferentLengths[length].insertWord(e)) {
			m++;
		}
		n++;
	}

	public int operation1() {
		return n;
	}

	public int operation2() {
		return m;
	}

	public int operation3(String word) {
		int length = word.length() - 1;
		Node<WordInformation> node = arrayOfDifferentLengths[length].Search(word);

		if (node != null) {
			return node.data.size;
		}
		return 0;
	}

	public int operation4(int length) {
		return arrayOfDifferentLengths[length - 1].size;
	}

	public void operation5() {
		int i = 0;
		while (i < sortedArray.length) {
			System.out.print("(" + sortedArray[i].word + "," + sortedArray[i].size + "), ");
			i++;
		}
		System.out.println();
	}

	public void operation6(String word) {
		Node<WordInformation> node = arrayOfDifferentLengths[word.length() - 1].Search(word);

		if (node != null) {
			System.out.println(node.data);
		} else {
			System.out.println("No such word..!");
		}
	}

	public boolean operation7(String w1, String w2) {
		int index = w1.length() - 1;
		Node<WordInformation> node1 = arrayOfDifferentLengths[index].Search(w1);
		index = w2.length() - 1;
		Node<WordInformation> node2 = arrayOfDifferentLengths[index].Search(w2);
		if (node1 != null && node2 != null) {
			Node<WordOccurrence> occ1 = node1.data.occList.head;
			Node<WordOccurrence> occ2 = node2.data.occList.head;
			while (occ1 != null) {

				int line = occ1.data.lineNo;
				int pos = occ1.data.position;
				while (occ2 != null) {
					if (checkAdjacent(line, occ2.data.lineNo, pos, occ2.data.position)) {
						return true;
					}
					if (occ2.data.lineNo > line) {
						break;
					}
					occ2 = occ2.next;
				}
				occ1 = occ1.next;
			}
		}
		return false;
	}

	private boolean checkAdjacent(int l1, int l2, int p1, int p2) {
		return (l1 == l2 && p1 == p2 - 1) || (l1 == l2 && p1 == p2 + 1);
	}

}
