import cv2
import mediapipe as mp
import math
import pyautogui

# Inicializar MediaPipe y PyAutoGUI
mp_drawing = mp.solutions.drawing_utils
mp_drawing_styles = mp.solutions.drawing_styles
mp_hands = mp.solutions.hands

def distance_between_points(point1, point2):
    """Calcula la distancia Euclidiana entre dos puntos 3D."""
    return math.sqrt((point2[0] - point1[0])**2 + 
                     (point2[1] - point1[1])**2 + 
                     (point2[2] - point1[2])**2)

def is_hand_open(hand_landmarks):
    """Determina si la mano está abierta basándose en la distancia entre la punta de los dedos y la base de los dedos."""
    # Coordenadas clave: base del dedo (nudo proximal) y punta del dedo
    thumb_tip = hand_landmarks.landmark[mp_hands.HandLandmark.THUMB_TIP]
    index_finger_tip = hand_landmarks.landmark[mp_hands.HandLandmark.INDEX_FINGER_TIP]
    middle_finger_tip = hand_landmarks.landmark[mp_hands.HandLandmark.MIDDLE_FINGER_TIP]
    ring_finger_tip = hand_landmarks.landmark[mp_hands.HandLandmark.RING_FINGER_TIP]
    pinky_tip = hand_landmarks.landmark[mp_hands.HandLandmark.PINKY_TIP]
    
    # Coordenadas del punto central de la palma (aproximado usando el nudo proximal del índice)
    palm_base = hand_landmarks.landmark[mp_hands.HandLandmark.WRIST]

    # Calcula la distancia entre las puntas de los dedos y la base de la palma
    dist_thumb = distance_between_points((thumb_tip.x, thumb_tip.y, thumb_tip.z), 
                                         (palm_base.x, palm_base.y, palm_base.z))
    dist_index = distance_between_points((index_finger_tip.x, index_finger_tip.y, index_finger_tip.z), 
                                         (palm_base.x, palm_base.y, palm_base.z))
    dist_middle = distance_between_points((middle_finger_tip.x, middle_finger_tip.y, middle_finger_tip.z), 
                                          (palm_base.x, palm_base.y, palm_base.z))
    dist_ring = distance_between_points((ring_finger_tip.x, ring_finger_tip.y, ring_finger_tip.z), 
                                        (palm_base.x, palm_base.y, palm_base.z))
    dist_pinky = distance_between_points((pinky_tip.x, pinky_tip.y, pinky_tip.z), 
                                         (palm_base.x, palm_base.y, palm_base.z))

    # Definir un umbral para considerar que la mano está abierta o cerrada
    umbral_abierto = 0.2
    dedos_abiertos = [dist_thumb, dist_index, dist_middle, dist_ring, dist_pinky]

    # Si la mayoría de los dedos están lejos de la palma, la mano está abierta
    if all(dist > umbral_abierto for dist in dedos_abiertos):
        return True
    else:
        return False

# For webcam input:
cap = cv2.VideoCapture(0)
with mp_hands.Hands(
    model_complexity=0,
    min_detection_confidence=0.5,
    min_tracking_confidence=0.5) as hands:
  while cap.isOpened():
    success, image = cap.read()
    if not success:
      print("Ignoring empty camera frame.")
      continue

    # Para mejorar el rendimiento, marcamos la imagen como no escribible
    image.flags.writeable = False
    image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    results = hands.process(image)

    # Dibuja las anotaciones en la imagen
    image.flags.writeable = True
    image = cv2.cvtColor(image, cv2.COLOR_RGB2BGR)

    if results.multi_hand_landmarks:
      for hand_landmarks in results.multi_hand_landmarks:
        mp_drawing.draw_landmarks(
            image,
            hand_landmarks,
            mp_hands.HAND_CONNECTIONS,
            mp_drawing_styles.get_default_hand_landmarks_style(),
            mp_drawing_styles.get_default_hand_connections_style())

        # Determinar si la mano está abierta o cerrada
        if is_hand_open(hand_landmarks):
            print("Mano abierta")
        else:
            print("Mano cerrada y probablemente esté cogiendo un objeto")

    # Muestra la imagen
    cv2.imshow('MediaPipe Hands', cv2.flip(image, 1))
    if cv2.waitKey(5) & 0xFF == 27:
      break

cap.release()

cap.release()







