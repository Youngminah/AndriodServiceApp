 
# 안드로이드 스튜디오를 이용하여 서비스와 스레드 사용하여 
[![image](https://img.shields.io/badge/Build-Gradle%205.1.1-yellowgreen)](https://gradle.org/) 
[![image](https://img.shields.io/badge/Android%20SDK-API%2029%3A%20Android%209.%2B-brightgreen) ](https://developer.android.com/studio/?gclid=CjwKCAjwh7H7BRBBEiwAPXjadjupXKrLjEJYAGBpFkUAfz0EV5_K5790QOU1YjOq933gNe1xoCs6IhoCKXQQAvD_BwE&gclsrc=aw.ds)
[![image](https://img.shields.io/badge/Language-Java-orange)](https://www.java.com/ko/)




#  :memo: 개요

Navigation목록에 Slideshow를 클릭하면 저장된 사진들을 마우스 이벤트에 따라 SlideShow로 보여주며, 사용자가 입력한 번호에 따라 사진을 보여주기도 하는 Slideshow Application이다.




# :memo: 실행사진



# :memo: 중요한 코드


## viewPager2

viewPager2는 이미지를 마우스 드래그에 따라 슬라이드로 이동시킬수 있게 해주는 view이다. 이것은 기능을 생성하는 Java에서 PagerAdapter를 상속해야하는데 마우스의 드래그가 일어나면 이벤트 처리를 해준다. 
이러한 어뎁터로 Fragment를 생성하여 viewpager2에 계속 추가를 해준다.


![image](https://user-images.githubusercontent.com/42762236/98674554-5140be80-239c-11eb-814c-6424bb499778.png)



즉 viewPager2를 slideshow.xml에서 추가해준뒤 그안에 넣을 Fragment에 해당하는 xml,java 도 생성해주어야한다.

fragment에 해당하는 것을 FragmentStateAdapter안에 넣어주기위해 FragmentStateAdapter 상속받는 어뎁터 class도 생성해야함. (아래 사진과같은 툴 class이름은 무관 Innerclass로 생성해도되고 밖에 생성해도됨)
![image](https://user-images.githubusercontent.com/42762236/98674594-60277100-239c-11eb-9b12-d3ba202d8260.png)

createFragment 메소드: 이게 중요한데 이부분에서 원하는 image를 넣은 fragment를 retrun해주면 그 이미지로 슬라이딩 되는 원리이다. 그렇다면 어떻게? (아래 이미지 Id를 가져오는 함수를 이용하면 쉬움)



getItemCount 메소드 : 그냥 슬라이딩대상의 사진이 총 몇개인지 리턴 . 이 프로젝트에선 30이 리턴되야됨.







## 이미지 Id 가져오기 🔑

```java
in drawable_id = getResources().getIdentifier("리소스이름", "drawable", getContext().getPackageName());
```          
getIdentifier 메소드는 리소스이름을 가지는 Drawable 폴더의 고유 아이디를 리턴한다.
고유아이디를 손쉽게 알아오면 그아이디를 이용하여 이미지뷰에 이미지를 변경할 수 있다.



응용하면 위에 createFragment 메소드에서
![image](https://user-images.githubusercontent.com/42762236/98674362-0c1c8c80-239c-11eb-93f2-9b78769ba4ee.png)

이렇게 return해주면, 클래스 필드이용하여 복잡하게 id를 가져올 필요없이 쉽게 바뀌는 것. 꿀팁
참고 : ImageViewFragment는 Fragment를 상속받는 class임 (보라색 형광펜 색칠한부분에서 class 생성한 그 클래스의 객체)


## setOnEditorActionListener (EditText에 이벤트 리스너)
입력창은 사용자가 입력할때마다 바뀌어야한다. 그래서 이벤트 리스너를 달아주어야하는데 사실 나는 다른것을 이용했지만 밑에 API를 이용하는 것이 정석적이고 좀 더 쉽다.
         
```java
editText.setOnEditorActionListener(new OnEditorActionListener() { 

	@Override

	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

		switch (actionId) {

		case EditorInfo.IME_ACTION_SEARCH:

			Toast.makeText(getApplicationContext(), "검색", Toast.LENGTH_LONG).show();
			break;

		default:

			Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_LONG).show();
			return false;

		}

		return true;

	}

});
``` 
v는 현재 editText를 TextView로 가져오며 v.getText로 내용을 가지고와서 1~30까지인지 검사 . 1~30까지라면 viewPager2에 setCurrentItem(int id) 메소드에 넣으면 자동으로 그 페이지로 변경됨







## viewPager2.setCurrentItem(int id)



id에 해당하는 pager로 이동됨 



## viewPager2.registerOnPageChangeCallback()



page가 바뀌면 실행되는 함수. 페이지가 바뀌면 입력창의 번호도 바뀌어야하기때문에 이 함수가 필요함. 
      



# :mailbox_with_mail: Connect

```

  Github : https://github.com/Youngminah
  Email : bluebelsmudi@gmail.com
  
```






