<template>
  <v-app id="inspire">
    <v-app-bar
      app
      color="white"
      flat
    >
      <v-container class="py-0 fill-height">
        <v-avatar
          class="mr-10"
          color="grey darken-1"
          size="32"
        ></v-avatar>

        <v-btn
          v-for="link in links"
          :key="link.href"
          :href="link.href"
          text
        >
          {{ link.title }}
        </v-btn>

        <v-spacer></v-spacer>

        <v-responsive max-width="260">
          <v-text-field
            dense
            flat
            hide-details
            rounded
            solo-inverted
          ></v-text-field>
        </v-responsive>
      </v-container>
    </v-app-bar>

    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col cols="2">
            <v-sheet rounded="lg">
              <v-list color="transparent">
                <v-list-item
                  @click="showTeam=true"
                ><!--클릭시 showTeam이 트루가 되도록 함-->
                  <v-list-item-content>
                    <v-list-item-title>
                      팀 보기<!--팀 멤버를 테이블로 보는 버튼-->
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>

                <v-divider class="my-2"></v-divider>

                <v-list-item
                  link
                  color="grey lighten-4"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      Refresh
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-sheet>
          </v-col>

          <v-col>
            <v-sheet
              min-height="70vh"
              rounded="lg"
            >
              <Team v-if="showTeam"></Team>
              <Player v-else v-model="players[index]" v-for="(player,index) in players" :key="index"></Player>
              <!--showTeam이 활성화되면 팀멤버 표를, 아니라면 스카우트할 선수 목록을 확인-->

            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Player from './components/Player'
import Team from './components/Team'

const axios=require('axios').default;//프론트엔드와 백엔드를 연결하기 위해 필요

export default {
  name: 'App',

  components: {
    Player,Team
  },

  data: () => ({
      links: [
        {title:'선수 양성',href:"/"}//선수양성 frontend로 이동
      ],
      players:[

      ],
      showTeam: false//기본적으로 false를 해두어 스카우트 창을 보여줌
    }),

  created(){
    this.init();
  },

    methods:{

      async init(){
        const response = await axios.get("/catchers");
        this.players=response.data._embedded.catchers;
      }
    }//프론트엔드에서 백엔드 http 주소를 불러와 사용
};
</script>
