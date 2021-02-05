# 2021-AmongEarth
![image](https://user-images.githubusercontent.com/39791467/107069603-88046480-6825-11eb-9215-2879329c1e6a.png)
어몽어스는 올바른 쓰레기 분리배출을 위한 서비스 입니다.

## 프로젝트 진행 과정
### 1. 데이터 수집 및 전처리
#### 1.1 데이터 수집  
- [trashnet](https://github.com/garythung/trashnet)  
- 약 3500개 이미지 크롤링  
#### 1.2 데이터 라벨링  
- [Yolo_mark](https://github.com/AlexeyAB/Yolo_mark)  

### 2. YoloV4 학습 및 tflite 변환
- [tensorflow-yolov4-tflite](https://github.com/hunglc007/tensorflow-yolov4-tflite)  
- [darknet](https://github.com/AlexeyAB/darknet)  
- pretrain된 weight를 사용해 학습  
- tflite로 변환

### 3. 안드로이드 앱 구성
![image](https://user-images.githubusercontent.com/39791467/107069401-4378c900-6825-11eb-99d0-ede99fd37617.png)
① MainActivity.java에서 분리배출 방법을 누르면 MediaScanner.java 가 실행되어 촬영 화면으로 연결된다.  
② 카메라로 분리배출하고자 하는 쓰레기 사진을 찍으면 assets/yolov4-custom.tflite 파일이 모델 실행을 위한 환경을 만들어준다.  
③ ‘결과보기’ Button을 누르면 YoloActivity.java 가 실행되어 yolov4-custom.tflite 의 모델을 실행한다.    
④ 모델의 결과값에 따라 method Package 내부의 쓰레기 분리배출 방법 중 해당하는 java 파일을 출력하게 된다. (ex. IcepackActivity.java)  


## ⭐중요⭐
[구글 드라이브](https://drive.google.com/drive/folders/1Hf4Ck5b45GCxzqQ8n4ru-g0bTl4--c4J?usp=sharing)에서 tflite를 다운 받아서 assets 폴더 아래 넣는다.

## Contributors ✨
<table>
  <tr>
    <td align="center"><a href="https://github.com/kohseongyeon"><img src="https://user-images.githubusercontent.com/39791467/107068644-3a3b2c80-6824-11eb-9f74-60b31184e714.png" width="100px;" alt=""/><br /><sub><b>고성연</b></sub></a></td>
    <td align="center"><a href="https://github.com/shpark0308"><img src="https://user-images.githubusercontent.com/39791467/107068286-c39e2f00-6823-11eb-9a7b-51a802b6036d.png" width="100px;" alt=""/><br /><sub><b>박신형</b></sub></a></td>
    <td align="center"><a href="https://github.com/omocomo"><img src="https://user-images.githubusercontent.com/39791467/107068723-50e18380-6824-11eb-8ae8-f986065bb011.png" width="100px;" alt=""/><br /><sub><b>이도연</b></sub></a></td>
    <td align="center"><a href="https://github.com/JIAH-LEA"><img src="https://user-images.githubusercontent.com/39791467/107068845-72426f80-6824-11eb-8e05-ae394346dcf3.png" width="100px;" alt=""/><br /><sub><b>이지아</b></sub></a></td>
  </tr>
</table>
