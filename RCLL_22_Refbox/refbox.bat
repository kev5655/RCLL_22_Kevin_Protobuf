docker run rm -it --name refbox --net=host -v /d/config.yaml:/etc/rcll-refbox/config.yaml -v /d/facts.clp:/usr/local/share/rcll-refbox/games/rcll/facts.clp robocuplogistics/rcll-refbox llsf-refbox
docker run -rm -it --name refbox-shell --net=host -e COLUMNS=120 -e LINES=40 robocuplogistics/rcll-refbox llsf-refbox-shell