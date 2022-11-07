package ru.ozon.route256.feature_profile.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import ru.ozon.route256.feature_profile.R
import ru.ozon.route256.feature_profile.databinding.ProfileFragmentBinding
import ru.ozon.route256.feature_profile.di.ProfileFeatureComponent
import ru.ozon.route256.feature_profile.presentation.view_models.ProfileViewModel
import ru.ozon.route256.feature_profile.presentation.view_objects.Gender
import ru.ozon.route256.feature_profile.presentation.view_objects.ProfileVO
import ru.ozon.route256.feature_profile.presentation.view_objects.Region
import ru.ozon.route256.feature_profile_api.ProfileNavigationApi
import javax.inject.Inject

class ProfileFragment : Fragment() {
    companion object {
        const val TAG = "PDPFragment"
    }

    private lateinit var binding: ProfileFragmentBinding

    @Inject
    lateinit var profileNavigationApi: ProfileNavigationApi

    @Inject
    lateinit var vm: ProfileViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProfileFeatureComponent.profileFeatureComponent?.inject(this)
    }

    override fun onPause() {
        if (isRemoving) {
            if (profileNavigationApi.isFeatureClosed(this)) {
                ProfileFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }

    override fun onStop() {
        val updatedProfile = ProfileVO(
            name = binding.personName.text.toString(),
            age = if (binding.personAge.text.toString() == "") null
            else binding.personAge.text.toString().toInt(),
            gender = getGenderFormSpinner(),
            region = getRegionFormSpinner()
        )
        vm.saveProfile(updatedProfile)
        super.onStop()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater)
        vm.getProfile()

        ArrayAdapter.createFromResource(
            requireContext(), R.array.genders_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.personGender.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(), R.array.regions_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.personRegion.adapter = adapter
        }

        vm.profileLD.observe(viewLifecycleOwner) { profile ->
            initView(profile)
        }
        return binding.root
    }

    private fun initView(profile: ProfileVO?) {
        binding.profileImage.setImageResource(R.drawable.ic_person)
        binding.personName.setText(profile?.name)
        binding.personAge.setText(profile?.age.toString())
        binding.personGender.setSelection(Gender.values().indexOf(profile?.gender))
        binding.personRegion.setSelection(Region.values().indexOf(profile?.region))
    }

    private fun getGenderFormSpinner(): Gender {
        return Gender.values()[binding.personGender.selectedItemPosition]
    }

    private fun getRegionFormSpinner(): Region {
        return Region.values()[binding.personRegion.selectedItemPosition]
    }

}