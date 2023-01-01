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
          href="/shop"
          text
        >
          스카우트
        </v-btn><!--스카우트 기능으로 넘어가는 버튼-->

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
          <v-col cols="3">
            <v-sheet rounded="lg">
              <v-list color="transparent">
                <v-list-item
                  v-for="item in items"
                  :key="item"
                  link
                  @click="addPlayer(item)"
                > <!--클릭시 player를 추가-->
                  <v-list-item-content>
                    <v-list-item-title>
                      {{ item }}
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
              <Player @change="updatePlayer(player)" :is="players[index].type" v-model="players[index]" v-for="(player,index) in players" :key="index"></Player>
              <!--player를 화면에 출력하는 frontend는 따로 분리함, index만큼 반복하며 players를 보여줌-->

            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Catcher from './components/Catcher' //import components.Catcher;와 같다
import Pitcher from './components/Pitcher'
import Defender from './components/Defender'
import Player from './components/Player';
const axios=require('axios').default;//프론트엔드와 백엔드를 연결하기 위해 필요

export default { //public class App{
  name: 'App',

  components: {
    Catcher, Pitcher, Defender,
    Player
  },

  data: () => ({
      items: [
        '포수',
        '투수',
        '수비수'
      ],

      players:[//player가 들어갈 자리
      ],
    }),

  created(){
    this.init();
  },

    methods:{

      async init(){
        const response = await axios.get("/catchers");//프론트엔드에서 백엔드 http 주소를 불러와 사용하는 것

        this.players=response.data._embedded.catchers;
      },

      addPlayer(item){
        //기본적인 포수의 시작 정보
        if(item=="포수"){
          this.players.push({
        type: 'Catcher',
        name: '포수',
        power: 4, speed:4
        });
        }else
        
        //기본적인 투수의 시작 정보
        if(item=="투수"){
          this.players.push({
        type: 'Pitcher',
        name: '투수',
        power: 2, speed:4
        });
        }else
        
        //기본적인 수비수의 시작 정보
        if(item=="수비수"){
          this.players.push({
        type: 'Defender',
        name: '수비수',
        power: 4, speed:2
        });
        }

        //catcher의 path로 가서 player 정보를 붙임
        axios.request({
          method: 'POST',
          url: `/catchers`,
          headers:{'Content-Type':'application/json'},
          data: this.players[this.players.length-1]
        });

      },

      //player의 path에서 pathname만을 떼어와 경로를 만들어 들어가고 player 정보를 수정함
      updatePlayer(player){
          axios.request({
          method: 'PATCH',
          url: new URL(player._links.self.href).pathname,
          headers:{'Content-Type':'application/json'},
          data: player
        });
      }

    }
};
</script>
