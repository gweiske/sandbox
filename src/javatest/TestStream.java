package javatest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestStream {

	public static void main(String[] args) {
		int maxLength = 2;
		List<String> strings = Arrays.asList(new String[] {"a", "b", "c", "d", "e", "f"});
		//		List<String> strings = Arrays.asList(new String[] {"a", "b", "c", "d"});
		List<List<String>> listOfSubLists;
		try {
			listOfSubLists = getSublists(strings, maxLength);
			System.out.println(listOfSubLists.size());
			listOfSubLists.stream().forEach(list -> System.out.println(list));
			
			listOfSubLists = getAllSublists(strings);
			System.out.println(listOfSubLists.size());
			List<List<String>> tList;
			tList = listOfSubLists.stream().filter(list -> list.size() == 1).collect(Collectors.toList());
			System.out.println(tList.size());
			tList.stream().forEach(list -> System.out.println(list));
			tList = listOfSubLists.stream().filter(list -> list.size() == 2).collect(Collectors.toList());
			System.out.println(tList.size());
			tList.stream().forEach(list -> System.out.println(list));
			tList = listOfSubLists.stream().filter(list -> list.size() == 3).collect(Collectors.toList());
			System.out.println(tList.size());
			tList.stream().forEach(list -> System.out.println(list));
			tList = listOfSubLists.stream().filter(list -> list.size() == 4).collect(Collectors.toList());
			System.out.println(tList.size());
			tList.stream().forEach(list -> System.out.println(list));
			tList = listOfSubLists.stream().filter(list -> list.size() == 5).collect(Collectors.toList());
			System.out.println(tList.size());
			tList.stream().forEach(list -> System.out.println(list));
			tList = listOfSubLists.stream().filter(list -> list.size() == 6).collect(Collectors.toList());
			System.out.println(tList.size());
			tList.stream().forEach(list -> System.out.println(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Given a list, generate all possible sublists of the specified size. The generator maintains 
	 * the order of the original list, no elements are duplicated. If n is the number of elements in 
	 * the original list, the number of sublists of length k is n!/((n-k)!*k!). The sublists are build
	 * by choosing an element from the element following the current element.
	 * @param list the original list from which to compute the sublists
	 * @param subListSize the size of the sublists
	 * @throws Exception when trying to create sublists that contain more elements than the original list
	 */
	public static <T> List<List<T>> getSublists(List<T> list, int subListSize) throws Exception {
		// sublist cannot be longer than original list
		if(subListSize > list.size()) {
			throw new Exception("A sublist cannot contain more elements than the orginal list.");
		}
		List<List<T>> listOfSubLists = new ArrayList<>();
		getSublists(list, new ArrayList<T>(), subListSize, listOfSubLists);
		return listOfSubLists;
	}

	/**
	 * Given a list, generate all possible sublists of the specified size. The generator maintains 
	 * the order of the original list, no elements are duplicated. If n is the number of elements in 
	 * the original list, the number of sublists of length k is n!/((n-k)!*k!). The sublists are build
	 * by choosing an element from the element following the current element.
	 * @param list the original list from which to compute the sublists
	 * @param sublist the current sublist, initially empty.
	 * @param subListSize the size of the sublists
	 * @param listOfSubLists list of sublists
	 */
	private static <T> void getSublists(List<T> list, List<T> sublist, int subListSize, List<List<T>> listOfSubLists) {
		// the sublist is complete when the desired size is reached
		if(sublist.size() == subListSize) {
			listOfSubLists.add(sublist);
		}
		else {
			// from the remaining list elements choose one to add the sublist currently being constructed
			for(int iListElem=0; iListElem<list.size(); iListElem++) {
				// create a new list containing all the elements computed so far
				List<T> newSublist = new ArrayList<T>();
				newSublist.addAll(sublist);
				// add an element from the remaining elements
				newSublist.add(list.get(iListElem));
				// repeat element selection with the ones following the element just selected
				getSublists(list.subList(iListElem + 1, list.size()), newSublist, subListSize, listOfSubLists);
			}		
		}
	}

	/**
	 * Generate all sublists of the provided list. The original order is maintained. The number of
	 * generated sublists is Sum(k=1..n) n!/((n-k)!*k!).
	 * @param list the original list
	 * @return the list of all sublists
	 */
	public static <T> List<List<T>> getAllSublists(List<T> list) {
		List<List<T>> listOfSubLists = new ArrayList<>();
		getAllSublists(list, list.size(), new ArrayList<T>(), listOfSubLists);
		return listOfSubLists;
	}

	private static <T> void getAllSublists(List<T> list, int origSize, List<T> sublist, List<List<T>> listOfSubLists) {
		if(sublist.size() < origSize) {
			for(int iListElem=0; iListElem<list.size(); iListElem++) {
				// create a new list containing all the elements computed so far
				List<T> newSublist = new ArrayList<T>();
				newSublist.addAll(sublist);
				// add an element from the remaining elements
				newSublist.add(list.get(iListElem));
				listOfSubLists.add(newSublist);
				// repeat element selection with the ones following the element just selected
				getAllSublists(list.subList(iListElem + 1, list.size()), origSize, newSublist, listOfSubLists);
			}		
		}
	}
}
