# 2021 - AmongEarth
 <p align="center"><img src="https://user-images.githubusercontent.com/60208434/111939670-3e8b8280-8b10-11eb-9881-9c8e859710d2.png"></p>
 <p align="center"><b>Among Earth</b> is a service for <br/>
 <br/>
1. statistical analysis of individual waste emissions<br/>
2. notification of correct recycle methods through object detection

## || IDE and Platform for Project
<table>
  <tr>
    <td align="center"><img src="https://1.bp.blogspot.com/-LgTa-xDiknI/X4EflN56boI/AAAAAAAAPuk/24YyKnqiGkwRS9-_9suPKkfsAwO4wHYEgCLcBGAsYHQ/s0/image9.png" width="70px;" height="70" alt=""/><br /><sub><b>Android</b></sub></td>
    <td>We used for <b>Android</b> for app development </td>
 </tr>
 <tr>
    <td align="center"><img src="https://www.gstatic.com/devrel-devsite/prod/v3069b08f6c9d146fa6a9c7f184b2836998ab5f12cefec1342a7bf6dca024aa94/firebase/images/touchicon-180.png" width="70px;" height="70" alt=""/><br /><sub><b>Firebase</b></sub></td>
    <td>We record personal data such as badge, waste record or trash pictures by using <b>Firebase<b/></td>
 </tr>
 <tr>
     <td align="center"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/Tensorflow_logo.svg/718px-Tensorflow_logo.svg.png" width="70px;" height="70" alt=""/><br /><sub><b>Tensorflow</b></sub></td>
  <td><b>Tensorflow</b> was used when we convert YoloV4 model into TFlite file.</td>
 </tr>
</table>

## || Our project functions
### 1. Introduce the proper waste disposal method
As you take a picture of the trash you want to throw away, our application will recognize the type of waste through object detection. Then this provides the correct method of recycling according to the type of trash. This will help users not only recycle waste more easily but also reduce waste of resources.
### 2. Record the amount of wastes
When you take a picture of the amount of trash you throw away everyday, our application will analyze the trash with statistics by recording daily wastes. Furthermore, depending on the amount of waste you discard, various environmental phrases will appear.
### 3. Zero Waste Challenge ( community )
With the photos you took above, you can actively participate in Zero Waste Challenge, which can reduce disposable waste. In addition, you can also share and communicate with various people with your records and story.
<br>

![image](https://user-images.githubusercontent.com/60208434/111959516-e82f3b80-8b31-11eb-87e2-cc77320fd29b.png)


## || Execution method
#### 1. ⭐ First, you should download TFLite from [Google drive](https://drive.google.com/drive/folders/1Hf4Ck5b45GCxzqQ8n4ru-g0bTl4--c4J?usp=sharing) and add it into assets folder
- you have to download yolov4-custom-30000.tflite from google drive.
- Then, you should add tflite into [assets folder](https://github.com/dsc-sookmyung/2021-AmongEarth/tree/main/AmongEarth/app/src/main/assets) in Android studio.
<p align="center"><img src="https://user-images.githubusercontent.com/60208434/111954437-7227d600-8b2b-11eb-9ea7-5e5dc9b41530.png"></p>

#### 2. ⭐ Please do not run on the emulator.
- Since our project uses mobile camera to detect trash object, our application do not work in virtual devices.
- So, you have to connect your real device with android studio and then run project.
<p align="center"><img src="https://user-images.githubusercontent.com/60208434/111956137-c338c980-8b2d-11eb-8c51-9139f92455a1.png"></p>

## || Contributors ✨
<table>
  <tr>
    <td align="center"><a href="https://github.com/kohseongyeon"><img src="https://user-images.githubusercontent.com/39791467/107068644-3a3b2c80-6824-11eb-9f74-60b31184e714.png" width="100px;" alt=""/><br /><sub><b>고성연</b></sub></a></td>
    <td align="center"><a href="https://github.com/shpark0308"><img src="https://user-images.githubusercontent.com/39791467/107068286-c39e2f00-6823-11eb-9a7b-51a802b6036d.png" width="100px;" alt=""/><br /><sub><b>박신형</b></sub></a></td>
    <td align="center"><a href="https://github.com/omocomo"><img src="https://user-images.githubusercontent.com/39791467/107068723-50e18380-6824-11eb-8ae8-f986065bb011.png" width="100px;" alt=""/><br /><sub><b>이도연</b></sub></a></td>
    <td align="center"><a href="https://github.com/JIAH-LEA"><img src="https://user-images.githubusercontent.com/39791467/107068845-72426f80-6824-11eb-8e05-ae394346dcf3.png" width="100px;" alt=""/><br /><sub><b>이지아</b></sub></a></td>
  </tr>
</table>
