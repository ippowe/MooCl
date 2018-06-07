<template lang="html">
  <!-- Slider main container -->
  <div class="swiper-container">
    <div class="swiper-wrapper">
      <div class="swiper-slide">Slide 1</div>
      <div class="swiper-slide">Slide 2</div>
      <div class="swiper-slide">Slide 3</div>
      <div class="swiper-slide">Slide 4</div>
      <div class="swiper-slide">Slide 5</div>
      <div class="swiper-slide">Slide 6</div>
      <div class="swiper-slide">Slide 7</div>
      <div class="swiper-slide">Slide 8</div>
      <div class="swiper-slide">Slide 9</div>
      <div class="swiper-slide">Slide 10</div>
    </div>
  </div>
</template>

<script>
import Swiper from 'swiper'

var mySwiper = new Swiper('.swiper-container', {
    speed: 400,
    spaceBetween: 100
});

export default {
  mySwiper,
}
</script>

<style lang="css">

</style>
