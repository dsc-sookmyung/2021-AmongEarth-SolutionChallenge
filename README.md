# 2021 Solution Challenge - AmongEarth
### 🌏 Among Earth is a service for
 1. statistical analysis of individual waste emissions<br/>
 2. guide of correct recycle methods through object detection

 ## 🎞 Demo Video
 [![demo video](https://user-images.githubusercontent.com/55048982/123397396-55dd7e80-d5dd-11eb-89f9-a8f30e6d6c4f.png)](https://youtu.be/2qMJX6ZLo8M)
 
 ## 🤳 How to use
![image (1)](https://user-images.githubusercontent.com/55048982/123410718-955e9780-d5ea-11eb-999a-9a86489311b6.png)
![image (2)](https://user-images.githubusercontent.com/55048982/123410727-97c0f180-d5ea-11eb-89ce-f1636de95ae6.png)
![image (3)](https://user-images.githubusercontent.com/55048982/123410734-98f21e80-d5ea-11eb-98e2-33e1eff24e35.png)
![image (4)](https://user-images.githubusercontent.com/55048982/123410739-9a234b80-d5ea-11eb-9518-8c53284a2bd0.png)
![image (5)](https://user-images.githubusercontent.com/55048982/123410746-9bed0f00-d5ea-11eb-8cf9-adcf3f888f2b.png)


 
 
## ⚙ IDE and Platform for Project
 
![tech flowchart](https://user-images.githubusercontent.com/55048982/123405364-b3c19480-d5e4-11eb-97b9-c6b2fa933628.png)

<table>
  <tr>
     <td align="center"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/Tensorflow_logo.svg/718px-Tensorflow_logo.svg.png" width="70px;" height="70" alt=""/><br /><sub><b>Tensorflow</b></sub></td>
  <td><b>Tensorflow</b> was used when we convert YoloV4 model into TFlite file.</td>
 </tr>
  <tr>
    <td align="center"><img src="https://1.bp.blogspot.com/-LgTa-xDiknI/X4EflN56boI/AAAAAAAAPuk/24YyKnqiGkwRS9-_9suPKkfsAwO4wHYEgCLcBGAsYHQ/s0/image9.png" width="70px;" height="70" alt=""/><br /><sub><b>Android</b></sub></td>
    <td>We used for <b>Android</b> for app development </td>
 </tr>
 <tr>
    <td align="center"><img src="https://www.gstatic.com/devrel-devsite/prod/v3069b08f6c9d146fa6a9c7f184b2836998ab5f12cefec1342a7bf6dca024aa94/firebase/images/touchicon-180.png" width="70px;" height="70" alt=""/><br /><sub><b>Firebase</b></sub></td>
    <td>We record personal data such as badge, waste record or trash pictures by using <b>Firebase<b/></td>
 </tr>
</table>



## 💻 Execution method
#### 1. First, you should download TFLite from [Google drive](https://drive.google.com/drive/folders/1Hf4Ck5b45GCxzqQ8n4ru-g0bTl4--c4J?usp=sharing) and add it into assets folder
- you have to download yolov4-custom-30000.tflite from google drive.
- Then, you should add tflite into [assets folder](https://github.com/dsc-sookmyung/2021-AmongEarth/tree/main/AmongEarth/app/src/main/assets) in Android studio.
<img src="https://user-images.githubusercontent.com/60208434/111954437-7227d600-8b2b-11eb-9ea7-5e5dc9b41530.png">

#### 2. Please do not run on the emulator.
- Since our project uses mobile camera to detect trash object, our application do not work in virtual devices.
- So, you have to connect your real device with android studio and then run project.
<img src="https://user-images.githubusercontent.com/60208434/111956137-c338c980-8b2d-11eb-8c51-9139f92455a1.png">


## ☘ Contributors 
<table>
  <tr>
    <td align="center"><a href="https://github.com/kohseongyeon"><img src="https://user-images.githubusercontent.com/39791467/107068644-3a3b2c80-6824-11eb-9f74-60b31184e714.png" width="100px;" alt=""/><br /><sub><b>SeongYeon Koh</b></sub></a></td>
    <td align="center"><a href="https://github.com/shpark0308"><img src="https://user-images.githubusercontent.com/39791467/107068286-c39e2f00-6823-11eb-9a7b-51a802b6036d.png" width="100px;" alt=""/><br /><sub><b>ShinHyeong Park</b></sub></a></td>
    <td align="center"><a href="https://github.com/omocomo"><img src="https://user-images.githubusercontent.com/39791467/107068723-50e18380-6824-11eb-8ae8-f986065bb011.png" width="100px;" alt=""/><br /><sub><b>Doyeon Lee</b></sub></a></td>
    <td align="center"><a href="https://github.com/JIAH-LEA"><img src="https://user-images.githubusercontent.com/39791467/107068845-72426f80-6824-11eb-8e05-ae394346dcf3.png" width="100px;" alt=""/><br /><sub><b>Jiah Lee</b></sub></a></td>
  </tr>
</table>
