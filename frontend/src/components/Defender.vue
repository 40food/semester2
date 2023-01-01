<template>

    <Player v-model="value"></Player>
    
</template>

<script>
  import Player from './Player'

  export default {
    name: 'Defender',

    components:{
      Player
    },

    mixins: [Player],

    methods:{
      }
    }
</script>
