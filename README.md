# texbot

A Clojure library designed to listen in a campfire room, filter messages to ones that have a specific prefix, interpret those as Tex expressions, generate an image of the expression, and paste it into the room.

Still incredibly beta.

## Usage

Replace the "cf-settings" and "room-name" in core.clj with your own credentials.  Then from a REPL in the namespace call

(listen-messages room-name).

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
