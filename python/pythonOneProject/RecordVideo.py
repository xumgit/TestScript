# coding: utf-8
from PIL import ImageGrab
import numpy as np
import cv2
import StressTest

class RecordVideo:
    def __init__(self):
        pass

    def startRecord(self):
        fps = 25
        start = 3  # delay time start
        end = 10  # end time
        
        curScreen = ImageGrab.grab()  # grab screen object
        height, width = curScreen.size
        
        video = cv2.VideoWriter('video02.avi', cv2.VideoWriter_fourcc(*'XVID'), fps, (height, width))
        
        imageNum = 0
        executeOne = True
        while True:
           
            imageNum += 1
            captureImage = ImageGrab.grab()  # grab window
            frame = cv2.cvtColor(np.array(captureImage), cv2.COLOR_RGB2BGR)
        
            # display without image window
            cv2.imshow('capturing', np.zeros((1, 255), np.uint8))
        
            # without image window position
            cv2.moveWindow('capturing', height - 100, width - 100)  
            if imageNum > fps * start:
                video.write(frame)

            # quite condition   
            if cv2.waitKey(50) == ord('q') or imageNum > fps * end:
                break 
            else:
                if (executeOne):
                    executeOne = False
                    #StressTest.startStress()
        video.release()
        cv2.destroyAllWindows()

if __name__ == '__main__':
    print("==========start Stress Test case==========") 
    StressTest.startStress()
    recordVideo = RecordVideo()
    recordVideo.startRecord()
    
    print("===========end Stress Test case===========")