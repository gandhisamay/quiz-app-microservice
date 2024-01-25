FROM 813361731051.dkr.ecr.ap-south-1.amazonaws.com/dockerhub:openjdk-8

ARG app=quiz-service
ARG version=ARTIFACT_VERSION
ARG lastCommitHash=ARTIFACT_COMMIT
ARG lastCommitAuthorEmail=ARTIFACT_COMMITTER


ENV ARTIFACT_NAME $app
ENV ARTIFACT_VERSION $version
ENV ARTIFACT_COMMIT $lastCommitHash
ENV ARTIFACT_COMMITTER $lastCommitAuthorEmail

ENV app $app

RUN mkdir -p /data/releases/$app/
RUN mkdir -p /logs/$app


ARG gid=2001
ARG uid=1001

RUN groupadd -g $gid -o $app
RUN useradd -m -u $uid -g $gid -o -s /bin/bash $app
RUN chown -R $app:$app /logs/$app
RUN chown -R $app:$app /data/releases/$app/
COPY ./entrypoint.sh /
RUN chmod +x entrypoint.sh
USER $app



COPY ./target/$app*.jar /data/releases/$app/

ENTRYPOINT ["/entrypoint.sh"]