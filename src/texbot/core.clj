(ns texbot.core
  (:require [clj-campfire.core :as cf]
            [texbot.creds :as creds])
  (:use [clojure.java.shell :only [sh]]))

(def trigger-string "$$")

(defn handle-message
  [msg]
  (try
    (println "msg initially: " msg)
    (if (= (subs (:body msg) 0 (count trigger-string)) trigger-string)
      (do
        (sh "./tex2im" "-r" "400x400" "-a" (subs (:body msg) (count trigger-string)))
        (cf/upload creds/cf-settings creds/room-name "out.png")))
    (catch Exception e
      (println "Got exception: " e))))

(defn message-handler-wrapper
  [msg-seq]
  (doall
   (map handle-message msg-seq)))

(defn listen-messages
  [room]
  (cf/stream-messages creds/cf-settings room message-handler-wrapper))

