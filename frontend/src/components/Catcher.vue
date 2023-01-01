<template>

    <Player v-model="value">
      <!--player에서 model을 불러옴-->

      <template v-slot:buttons>
      <v-btn
        outlined
        rounded
        text

        @click="throwball"
      >송구하기</v-btn>
      </template>
      <!--model에 송구하기 버튼 추가-->

    </Player>
    
</template>

<script>
  const axios=require('axios').default;
  import Player from './Player'

  export default {
    name: 'Catcher',

    components:{
      Player
    },

    mixins: [Player],

    methods:{
      //throwball 기능 추가
      async throwball(){
        await axios.request({
          method: 'PUT',
          url: new URL(this.value._links.throwball.href).pathname,
          headers:{'Content-Type':'application/json'}
        });

        this.refresh();
      }
    }

  }
</script>
