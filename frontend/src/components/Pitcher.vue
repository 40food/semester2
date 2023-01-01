<template>
  
    <Player v-model="value"></Player>
    <!--player에서 model을 불러옴-->

</template>

<script>
import Player from './Player'

  export default {
    name: 'Pitcher',

    components:{
      Player
    },

    mixins: [Player],

    methods:{
    }

  }
</script>
