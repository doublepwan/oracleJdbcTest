package pro05;

import java.util.Scanner;

public class BookShopApp {

	public static void main(String[] args) {
		
		BookShopDao dao = new BookShopDao();
		BookVo vo = new BookVo();
		
		vo.setTitle( "트와일라잇" );
		vo.setAuthorName( "스테파니메이어" );
		dao.insert( vo );
		
		vo.setTitle( "뉴문" );
		vo.setAuthorName( "스테파니메이어" );
		dao.insert( vo );
		
		vo.setTitle( "이클립스" );
		vo.setAuthorName( "스테파니메이어" );
		dao.insert( vo );
		
		vo.setTitle( "트와일라잇" );
		vo.setAuthorName( "스테파니메이어" );
		dao.insert( vo );
		
		vo.setTitle( "브레이킹던" );
		vo.setAuthorName( "스테파니메이어" );
		dao.insert( vo );
		
		vo.setTitle( "아리랑" );
		vo.setAuthorName( "조정래" );
		dao.insert( vo );
		
		vo.setTitle( "젊은그들" );
		vo.setAuthorName( "김동인" );
		dao.insert( vo );
		
		vo.setTitle( "아프니까 청춘이다" );
		vo.setAuthorName( "김난도" );
		dao.insert( vo );
		
		vo.setTitle( "귀천" );
		vo.setAuthorName( "천상병" );
		dao.insert( vo );
		
		vo.setTitle( "태백산맥" );
		vo.setAuthorName( "조정래" );
		dao.insert( vo );
		
		vo.setTitle( "풀하우스" );
		vo.setAuthorName( "원수연" );
		dao.insert( vo );
		
		Scanner key = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요.:");
		int num = key.nextInt();
		
		dao.rent(num, vo);
		
		displayBookInfo();
	}
	
	public static void displayBookInfo() {
		BookShopDao dao = new BookShopDao();
		System.out.println("*****도서 정보 출력하기******");
		dao.printAllBook();
	}	
}
