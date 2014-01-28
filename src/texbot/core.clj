(ns texbot.core
  (:require [clj-campfire.core :as cf])
  (:use [clojure.java.shell :only [sh]]))

(def trigger-string "$$")

(def cf-settings {:api-token "xxxxx"
                  :ssl true
                  :sub-domain "xxx"})

(def room-name "xxx")

(defn handle-message
  [msg]
  (try
    (println "msg initially: " msg)
    (if (= (subs (:body msg) 0 2) "$$")
      (do
        (sh "./tex2im" "-r" "400x400" "-a" (subs (:body msg) 2))
        (cf/upload cf-settings room-name "out.png")))
    (catch Exception e
      (println "Got exception: " e))))

(defn message-handler-wrapper
  [msg-seq]
  (doall
   (map handle-message msg-seq)))

(defn listen-messages
  [room]
  (cf/stream-messages cf-settings room message-handler-wrapper))

